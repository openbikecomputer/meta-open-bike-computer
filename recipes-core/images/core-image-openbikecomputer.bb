# Base this image on core-image-base
inherit core-image
LICENSE = "MIT"

IMAGE_INSTALL:append = " wayland weston"
IMAGE_INSTALL:append = " bryton860-core"
