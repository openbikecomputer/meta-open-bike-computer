# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

HOMEPAGE = "https://docs.lvgl.io/latest/en/html/porting/index.html"
SUMMARY = "LVGL's Display and Touch pad drivers"
DESCRIPTION = "Collection of drivers: SDL, framebuffer, wayland and more..."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d6fc0df890c5270ef045981b516bb8f2"

SRC_URI = "git://github.com/lvgl/lv_drivers;destsuffix=${S};protocol=https;nobranch=1"
SRCREV = "71830257710f430b6d8d1c324f89f2eab52488f1"

DEPENDS = "libxkbcommon lvgl wayland wayland-protocols"

REQUIRED_DISTRO_FEATURES = "wayland"

inherit cmake
inherit features_check

S = "${WORKDIR}/${PN}-${PV}"

EXTRA_OECMAKE += "-Dinstall:BOOL=ON -DLIB_INSTALL_DIR=${BASELIB} -DWAYLAND_PROTOCOLS_BASE=${RECIPE_SYSROOT}/usr/share/wayland-protocols -DWAYLAND_PROTOCOLS_DIR=${RECIPE_SYSROOT}/usr/include/"

TARGET_CFLAGS += "-DLV_CONF_INCLUDE_SIMPLE=1"
TARGET_CFLAGS += "-I${RECIPE_SYSROOT}/${includedir}/lvgl"

FILES:${PN}-dev += "\
    ${includedir}/lvgl/lv_drivers/ \
    "

do_compile:prepend() {
	# Generate wayland xdg protocol source file
	cd ${S}/wayland/
	cmake .
	make
}

do_install:append() {
	# Install generated wayland protocol in destination include folder
	install -d ${D}/usr/include/lvgl/lv_drivers/wayland/protocols
	install -m 644 ${S}/wayland/protocols/wayland-xdg-shell-client-protocol.h ${D}/usr/include/lvgl/lv_drivers/wayland/protocols/
	install -m 644 ${S}/wayland/protocols/wayland-xdg-shell-client-protocol.c ${D}/usr/include/lvgl/lv_drivers/wayland/protocols/
}
