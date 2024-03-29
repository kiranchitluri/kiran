#!/bin/bash

# creates binary distribution package

set -e

./make-clean.sh

VERSION=`gradle properties -q | grep "version:" | awk '{print $2}'`
DIRNAME="p2rank_$VERSION"

mkdir -p build

cp -rafv distro build/${DIRNAME}
(
    cd build
    GZIP_OPT=-9 tar cvzf ${DIRNAME}.tar.gz ${DIRNAME}
    rm -rf ${DIRNAME}
)

echo
echo "DISTRO BUILD: build/${DIRNAME}.tar.gz"
echo DONE