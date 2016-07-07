DESCRIPTION = "Hardware drivers for SPYCAT Spycat"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "spycat"

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
	install -m 0644 ${S}/lib/modules/${KV}/extra/brcmstb-spycat.ko ${D}${base_libdir}/modules/${KV}/extra
	install -m 0644 ${S}/lib/modules/${KV}/extra/ci.ko ${D}${base_libdir}/modules/${KV}/extra
	install -m 0644 ${S}/lib/modules/${KV}/extra/fts260.ko ${D}${base_libdir}/modules/${KV}/extra
	install -m 0644 ${S}/lib/modules/${KV}/extra/sp988x.ko ${D}${base_libdir}/modules/${KV}/extra
	install -m 0644 ${S}/lib/modules/${KV}/extra/sp968x.ko ${D}${base_libdir}/modules/${KV}/extra

	install -d ${D}${sysconfdir}/modules-load.d
	echo brcmstb-spycat >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
	echo ci >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
	echo fts260 >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
	echo sp988x >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
	echo sp968x >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
}

SRC_URI[md5sum] = "0367ab5f4dc0781c3fdf1b433e20447a"
SRC_URI[sha256sum] = "e953f982ca988949c63ef0acd62c183fe42b51a84e5ef82d20c6bdacaeb17201"
