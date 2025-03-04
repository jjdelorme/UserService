# Acme Enterprise User Application

This application demonstrates a couple of enterprise coding standards:

1. All files contain a copyright header
1. All DTOs are named with a `xxxxDTO` suffix
1. All DTOs use [Lombok](https://projectlombok.org/) for `@Getter` and `@Setter` generation
1. All services that create, update or delete data are annotated with `@AuditLog()` whereas read methods are not

