#!/usr/bin/env bash
#
# Requires: jdk-11+
# Requires: "mvn install" to have been executed before calling this script to generate the Jar file

set -e

dir=$(cd `dirname $0` && pwd)
cd ${dir}
scriptName=`basename "$0"`
usage="Usage: ./${scriptName} /path/to/file.log"

if [[ $# -lt 1 ]]
then
  echo "Invalid argument!"
  echo $usage
  exit 1
fi

java -cp ../../../target/log-file-parser-1.0-SNAPSHOT-jar-with-dependencies.jar \
	com.brad.logparser.Main \
	$1