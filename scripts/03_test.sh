#!/usr/bin/env bash
rm -rf app/build/test-results/debug/*.xml
./gradlew --project-cache-dir .gradle :app:test --rerun-tasks
