#!/bin/bash
BASEDIR=$(cd `dirname "${0}"` && pwd)
echo "Base dir: ${BASEDIR}"
docker run -it -v "${BASEDIR}":/documents asciidoctor/docker-asciidoctor "docs/build.sh"