#!/bin/sh
set -eu

cleanup() {
  echo "Cleaning..."
  docker compose down -v --remove-orphans
}

trap cleanup EXIT

start() {
  docker compose up -d --build

  TIMEOUT=60
  INTERVAL=2
  ELAPSED=0

  while [ "$ELAPSED" -lt "$TIMEOUT" ]; do
      if curl -sf "http://localhost:8100/actuator/health" | grep -q '"status":"UP"'; then
          echo "Identity service is started"
          return 0
      fi

      sleep "$INTERVAL"
      ELAPSED=$((ELAPSED + INTERVAL))
  done

  echo "ERROR: Identity service startup timeout"
  docker compose logs --tail=50
  return 1
}

start