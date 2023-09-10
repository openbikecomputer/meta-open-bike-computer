LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
    git://git@github.com/openbikecomputer/openbikecomputer-core.git;protocol=ssh;branch=main \
    file://openbikecomputer-core.service \
"

PV = "1.0+git${SRCPV}"
SRCREV = "12df73571471ffb8a0b3264c667b8665cc0932d3"

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

