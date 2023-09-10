LICENSE = "GPL3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5e2a33e58b999de64e423845a57990ef"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
    git://git@github.com/openbikecomputer/openbikecomputer-core.git;protocol=ssh;branch=master \
    file://openbikecomputer-core.service \
"

PV = "1.0+git${SRCPV}"
SRCREV = "01d608749a1aae2ace2738bf53d1e6b7b5e2bf08"

S = "${WORKDIR}/git"

inherit systemd
SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "openbikecomputer-core.service"

DEPENDS += " \
            lvgl \
            lv-drivers \
"

RDEPENDS:${PN} += " \
                   weston-init \
"

FILES:${PN} = "/lib/systemd/system/openbikecomputer-core.service /usr/bin/openbikecomputer-core"

CFLAGS += " -DLV_CONF_INCLUDE_SIMPLE=1 -DLV_LVGL_H_INCLUDE_SIMPLE=1"
EXTRA_OEMAKE += " SYSROOT=${RECIPE_SYSROOT}"

do_compile () {
	oe_runmake
}

do_install () {
	oe_runmake install 'DESTDIR=${D}/${bindir}'
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/openbikecomputer-core.service ${D}${systemd_unitdir}/system
}

