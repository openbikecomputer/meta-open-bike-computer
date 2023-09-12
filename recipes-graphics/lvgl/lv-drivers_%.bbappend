FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
FILESEXTRAPATHS:prepend := "${THISDIR}/lvgl:"

SRC_URI += " \
            file://lv_drv_conf.h \
            file://lv_conf.h \
            "

do_configure:append() {
	cp ${WORKDIR}/lv_drv_conf.h ${S}/lv_drv_conf.h
	cp ${WORKDIR}/lv_conf.h ${S}/lv_conf.h
}
