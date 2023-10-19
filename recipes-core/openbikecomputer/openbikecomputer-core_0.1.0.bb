LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
    git://git@github.com/openbikecomputer/openbikecomputer-core.git;protocol=ssh;branch=main \
    file://openbikecomputer-core.service \
"

PV = "0.1.0+git${SRCPV}"
SRCREV = "9e116c8ee82e52f563e8e496d611a5d422f5c7e7"

S = "${WORKDIR}/git"

inherit systemd
SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "openbikecomputer-core.service"

DEPENDS = " \
   lvgl \
   lv-drivers \
   libconfig \
"

RDEPENDS:${PN} = " \
    weston-init \
    libconfig \
"

FILES:${PN} = " \
	/lib/systemd/system/openbikecomputer-core.service \
	/usr/bin/openbikecomputer-core \
	/etc/openbikecomputer/*.conf \
"

CFLAGS += " -DLV_CONF_INCLUDE_SIMPLE=1 -DLV_LVGL_H_INCLUDE_SIMPLE=1"
EXTRA_OEMAKE += " SYSROOT=${RECIPE_SYSROOT}"

do_compile () {
	oe_runmake
}

do_install () {
	oe_runmake install 'ROOTDIR=${D}' 'BINDIR=${bindir}'
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/openbikecomputer-core.service ${D}${systemd_unitdir}/system
}

