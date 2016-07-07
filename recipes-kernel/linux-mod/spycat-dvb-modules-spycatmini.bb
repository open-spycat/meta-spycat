DESCRIPTION = "Hardware drivers for SPYCAT Spycatmini"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "spycatmini"

KV = "4.6.3"
SRCDATE = "20160707"

PV = "${KV}+${SRCDATE}"
PR = "r0"

RDEPENDS_${PN} += "spycat-firmware-mn8847x"

SRC_URI = "https://github.com/open-spycat/spycat-dvb-modules-${MACHINE}/raw/master/linux-${KV}-${MACHINE}-${SRCDATE}.tar.gz"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

inherit module

FILES_${PN} += "${sysconfdir}/modules-load.d/_${MACHINE}.conf"

do_compile() {
}

do_install() {
	install -d ${D}${base_libdir}/modules/${KV}/extra
	install -m 0644 ${S}/lib/modules/${KV}/extra/brcmstb-spycatmini.ko ${D}${base_libdir}/modules/${KV}/extra
	install -m 0644 ${S}/lib/modules/${KV}/extra/fts260.ko ${D}${base_libdir}/modules/${KV}/extra
	install -m 0644 ${S}/lib/modules/${KV}/extra/sp988x.ko ${D}${base_libdir}/modules/${KV}/extra

	install -d ${D}${sysconfdir}/modules-load.d
	echo brcmstb-spycatmini >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
	echo fts260 >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
	echo sp988x >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
}

SRC_URI[md5sum] = "9c5222ee3d24fa8d4b5214419b1479ef"
SRC_URI[sha256sum] = "1503d2647579f6ad34fd9387ddf5d30620026adb3af0bfa8b8f5b7b206ec28fb"
