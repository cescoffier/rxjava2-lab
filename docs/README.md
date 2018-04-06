# Build

To build the documentation:

* Using Fish:

```bash
docker run -it -v (pwd):/documents/ asciidoctor/docker-asciidoctor "./build.sh" "html"
```

* Using Bash

```bash
docker run -it -v $(pwd):/documents/ asciidoctor/docker-asciidoctor "./build.sh" "html"
```
