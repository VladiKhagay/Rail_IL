#!/usr/bin/python
import subprocess

from flask import Flask, request

app = Flask("my_app1")


@app.route("/rail")
def rail_schedule():
    if 'source' in request.args:
        source = request.args.get('source')
    if 'destination' in request.args:
        destination = request.args.get('destination')
    if 'hour' in request.args:
        hour = request.args.get('hour')
    if 'minutes' in request.args:
        minutes = request.args.get('minutes')

    if 'outformat' in request.args:
        outformat = request.args.get('outformat')
    else:
        outformat = 'html'

    return subprocess.check_output(["java", "-classpath", "/home/vladi/java-workspace/railApp/bin", "UserApp",
                                    source, destination, hour, minutes, outformat])


app.run(port=8005, host="0.0.0.0")
