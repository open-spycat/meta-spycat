DESCRIPTION = "Hardware drivers for SPYCAT Spycat"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "spycat"

KV = "4.9.0"
SRCDATE = "20170131"

PV = "${KV}+${SRCDATE}"
PR = "r0"

RDEPENDS_${PN} += "spycat-firmware-mn8847x"

SRC_URI = "https://github.com/open-spycat/spycat-dvb-modules/raw/master/${MACHINE}-drivers-${KV}-${SRCDATE}.zip"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

inherit module

FILES_${PN} += "${sysconfdir}/modules-load.d/_${MACHINE}.conf"

do_compile() {
}

do_install() {
	install -d ${D}${base_libdir}/modules/${KV}/extra
	install -m 0644 ${S}/brcmstb-spycat.ko ${D}${base_libdir}/modules/${KV}/extra
	install -m 0644 ${S}/ci.ko ${D}${base_libdir}/modules/${KV}/extra
	install -m 0644 ${S}/fts260.ko ${D}${base_libdir}/modules/${KV}/extra
	install -m 0644 ${S}/sp988x.ko ${D}${base_libdir}/modules/${KV}/extra
	install -m 0644 ${S}/sp968x.ko ${D}${base_libdir}/modules/${KV}/extra

	install -d ${D}${sysconfdir}/modules-load.d
	echo brcmstb-spycat >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
	echo ci >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
	echo fts260 >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
	echo sp988x >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
	echo sp968x >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
}

SRC_URI[md5sum] = "1526a3eaeb551f04c9a0058ac8abb7d9"
SRC_URI[sha256sum] = "e369ddcf1d47c51043c56949f1ffcd158fa5dee9f78e7934b27631a01f5589f1"
