FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
            file://bryton860_lv_drv_conf.h \
            file://bryton860_lv_conf.h \
            "

do_configure:append() {
	cp ${WORKDIR}/bryton860_lv_drv_conf.h ${S}/lv_drv_conf.h
	cp ${WORKDIR}/bryton860_lv_conf.h ${S}/lv_conf.h
}
