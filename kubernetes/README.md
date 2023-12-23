# Minikube

The project is based on a Minikube single cluster Kubernetes instance. To install minikube just follow this guide https://minikube.sigs.k8s.io/docs/start.

# Import local images
To import local images in minikube we can first list the local docker images

```bash
docker images
```

If we have some build images with the latest version we must provide a **tag**, like `v1`.


```bash
docker tag unibo-cybersec/openapi-oauth-client unibo-cybersec/openapi-oauth-client:v1
```

Then we can import containers inside minikube with

```bash
minikube image load unibo-cybersec/openapi-oauth-client:v1
```

We can list minikube containers with

```bash
minikube image ls
```

At the end, we can remove an image with

```bash
minikube image rm docker.io/unibo-cybersec/openapi-oauth-client:v1
```

Expose services with Minikube 

```bash
minikube service --all
```

Reverse proxy to test services
```bash
ssh -N -D 0.0.0.0:1234 k8s@<IP>
```
