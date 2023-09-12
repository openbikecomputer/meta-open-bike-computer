# Base this image on core-image-base
inherit core-image
LICENSE = "MIT"

IMAGE_INSTALL:append = " wayland weston"
IMAGE_INSTALL:append = " openbikecomputer-core"

# Make the image suitable for development
IMAGE_FEATURES:append = " debug-tweaks ssh-server-openssh tools-debug"
