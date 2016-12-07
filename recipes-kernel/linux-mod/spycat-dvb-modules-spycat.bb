DESCRIPTION = "Hardware drivers for SPYCAT Spycat"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "spycat"

KV = "4.8.0"
SRCDATE = "20161207"

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

SRC_URI[md5sum] = "d192998bc210f7968c5529bcb9558a75"
SRC_URI[sha256sum] = "e190410829eb8e2629b6294a8b7e74230fd2c762c5c95aae27887847b9e5c586"
