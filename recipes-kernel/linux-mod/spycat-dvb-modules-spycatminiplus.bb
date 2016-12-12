DESCRIPTION = "Hardware drivers for SPYCAT Spycatmini+"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "spycatmini"

KV = "4.9.0"
SRCDATE = "20161212"

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
	install -m 0644 ${S}/brcmstb-spycatminiplus.ko ${D}${base_libdir}/modules/${KV}/extra
	install -m 0644 ${S}/fts260.ko ${D}${base_libdir}/modules/${KV}/extra
	install -m 0644 ${S}/sp988x.ko ${D}${base_libdir}/modules/${KV}/extra
	install -m 0644 ${S}/ftm4862.ko ${D}${base_libdir}/modules/${KV}/extra

	install -d ${D}${sysconfdir}/modules-load.d
	echo brcmstb-spycatminiplus >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
	echo fts260 >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
	echo sp988x >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
	echo ftm4862 >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
}

SRC_URI[md5sum] = "835bc09c0041322757a319675e188d0c"
SRC_URI[sha256sum] = "87765dc972bdde5545e03fee61057ad8d13c8cf336c4ed1b2f3f0646f46aee88"
