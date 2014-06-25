# s3sync
s3sync is concurrent s3 uploader.

# How to build
```bash
gradle build
```

# How to execute
```bash
java -jar ./build/libs/s3sync-1.0.jar -P -c $HOME/.s3sync --header "Cache-Control: public, max-age=86400" --concurrent 50 --bucket s3://BUCKET_NAME/path/to/directory --dir /path/to/upload/directory
```
