#!/bin/bash

set -e # Stop script it any commands fail

aws --endpoint-url=http://localhost:4566 cloudformation deploy \
    --stack-name patient-management \
    --template-file "/home/druth/coding-projects/patient-management-system/cdk.out/localStack.template.json"

aws --endpoint-url=http://localhost:4566 elbv2 describe-load-balancers \
    --query "LoadBalancers[0].DNSName" --output text