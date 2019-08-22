#!/bin/bash

ASADMIN=$macosx/Users/user-tmp/Downloads/payara5/bin/asadmin 
function undeploy_all {
    for p in $*; do
        echo "Undeploying $p..."
        $ASADMIN  --user admin undeploy $p
    done;
}

apps=`$ASADMIN list-applications  --user admin`

undeploy_all $apps