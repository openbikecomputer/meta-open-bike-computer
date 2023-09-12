FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://lv_conf.h"

do_configure:append() {
	cp ${WORKDIR}/lv_conf.h ${S}/lv_conf.h
}
