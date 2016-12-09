SUMMARY = "Driver for Realtek 8723BS wireless/bluetooth devices"
HOMEPAGE = "http://www.realtek.com/"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
require conf/license/license-gplv2.inc

PR = "r0"

SRC_URI = "https://github.com/anthonywong/rtl8723bs/archive/v${PV}.tar.gz \
	file://rtl8723bs-makefile.patch \
	file://rtl8723bs-remove-debug.patch \
	file://rtl8723bs-gcc5.patch \
	file://rtl8723bs-add-4.8-support.patch \
	"

S = "${WORKDIR}/rtl8723bs-${PV}"

inherit module

do_install() {
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/staging/rtl8723bs
	install -m 0644 ${S}/r8723bs.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/staging/rtl8723bs
}

python do_package_prepend() {
    d.appendVar('PKGV', '-')
    d.appendVar('PKGV', d.getVar("KERNEL_VERSION", True).split("-")[0])
}

RDEPENDS_${PN} = "firmware-rtl8723bs"

SRC_URI[md5sum] = "6003f12a873946bc56f495391705e729"
SRC_URI[sha256sum] = "6a66855c3aec845e531e77efca06364b3bbc4d052eb527a002f8c801c9106b40"
