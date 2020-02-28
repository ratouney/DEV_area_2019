# Android Build

### To build the Docker

    $ ./scripts/build_docker.sh

### To build the android project

    $ ./scripts/run_docker.sh ./scripts/compile.sh

#### Known issue n°1:

    > sh: 1: ./scripts/compile.sh: Permission denied

SELinux may be blocking you, to deactivate SELinux :

    $ sudo setenforce 0

to reactivate SELinux later:

    $ sudo setenforce 1

#### Known issue n°2:
    None
