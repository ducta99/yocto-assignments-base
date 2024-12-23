LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/ducta99/aesd-assignments-ducta99.git;protocol=ssh;branch=assignment6"
PV = "1.0+git${SRCPV}"
SRCREV = "1ebf1a6de9d5e1ff53604c270e59e1ed7ea999ed"

S = "${WORKDIR}/git/server"

FILES:${PN} += "${bindir}/aesdsocket"
TARGET_LDFLAGS = "-pthread -lrt" 
DEPENDS_${PN} = "libgcc"
RDEPENDS_${PN}  = "libgcc"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME = "aesdsocket-init"
INITSCRIPT_PARAMS = "defaults 99"
inherit update-rc.d

do_configure() {
    :
}

do_compile() {
	cd "${S}"
	oe_runmake CC="${CC}" LDFLAGS="${TARGET_LDFLAGS}"
}

do_install() {
	install -d ${D}${bindir}
    install -d ${D}/usr/bin
    install -m 0755 ${S}/aesdsocket ${D}/usr/bin/aesdsocket
    install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}
}
