#!/usr/bin/env bash

set -o errexit
set -o pipefail
set -o nounset

script_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

cd "$script_dir/.."
docker-compose up -d order-db inventory-db crm-db localstack
