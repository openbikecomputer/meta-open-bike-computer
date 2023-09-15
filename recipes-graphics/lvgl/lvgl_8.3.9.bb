# SPDX-FileCopyrightText: Huawei Inc.
#
# SPDX-License-Identifier: MIT

HOMEPAGE = "https://lvgl.io/"
DESCRIPTION = "LVGL is an OSS graphics library to create embedded GUI"
SUMMARY = "Light and Versatile Graphics Library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENCE.txt;md5=bf1198c89ae87f043108cea62460b03a"

SRC_URI = "gitsm://github.com/lvgl/lvgl;destsuffix=${S};protocol=https;nobranch=1"
SRCREV = "9ec3b8706b098f60940cee6ac211d3cb3141aedf"

REQUIRED_DISTRO_FEATURES = "wayland"

inherit cmake
inherit features_check

EXTRA_OECMAKE = "-DLIB_INSTALL_DIR=${BASELIB} -DLV_CONF_INCLUDE_SIMPLE=1"
S = "${WORKDIR}/${PN}-${PV}"

FILES:${PN}-dev += "\
    ${includedir}/${PN}/ \
    ${includedir}/${PN}/lvgl/ \
"
