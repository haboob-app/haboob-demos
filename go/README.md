# Haboob Go Demo
Example of how to use haboob with Go

## Prerequisites

You will need the following things properly installed on your computer.

* [Go](https://golang.org/dl/)
* [go-yaml](https://github.com/go-yaml/yaml) on your Go workspace

## Installation

* `git clone git@github.com:haboob-app/haboob-demos.git`
* `cd haboob-demos/go`
* Copy the `haboob.go` file and [Configuration File](../config/default.yaml) to the src folder of the Go workspace

## Test

On the Go src workspace folder, execute:

* `go run haboob.go` - test with development environment
* `go run haboob.go -environment=production` - test with production environment
