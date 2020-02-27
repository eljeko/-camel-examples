#!/usr/bin/env bash
curl --header "Content-Type: application/json" --request POST --data @$1 localhost:8080/cbr/ingestion/