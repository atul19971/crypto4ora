# crypto4ora
crypto4ora provide simple and enhanced assymetric cryptography capability in Oracle database. 
# Features

- Crypto4ora has implemented the assymetric(RSA) cryptography.
- public/private key generator for RSA algorithm.
- Encryption using public key.
- Decription using private key.

# Docs
- [Installation Guideline](#installation-guideline)
  - [Command Execution](#command-execution)
  - [SQL Script](#sql-script)
- [Usage](#usage)
  - [Generating Keys](#generating-keys)
  - [Encryption](#encryption)
  - [Decryption](#decryption)
- [Roadmap](#roadmap)

## Installation Guideline

Download the zip [crypto4ora](https://github.com/atul19971/crypto4ora/tree/master/zip/crypto4ora.zip) crypto4ora which contains `crypto4ora.jar` and `crypto4ora.sql`.


### Command Execution
Execute below command for loading the `crypto4ora.jar` in oracle DB.
```xml
loadjava -f -verbose -oracleresolver -resolve -oci8 -u user/password@service crypto4ora.jar
```


### SQL Script
Once load java executed succesfully run the script crypto4ora.sql in your required schema.

```sql
CREATE OR REPLACE PACKAGE CRYPTO AS 
FUNCTION RSA_ENCRYPT(PLAIN_TEXT VARCHAR2,PUBLIC_KEY VARCHAR2) RETURN VARCHAR2
AS
LANGUAGE JAVA NAME 'com/dishtavar/crypto4ora/RSAUtil.encrypt (java.lang.String,java.lang.String) return java.lang.String';
FUNCTION RSA_DECRYPT(ENCRYPTED_TEXT VARCHAR2,PRIVATE_KEY VARCHAR2) RETURN VARCHAR2
AS
LANGUAGE JAVA NAME 'com/dishtavar/crypto4ora/RSAUtil.decrypt (java.lang.String,java.lang.String) return java.lang.String';
FUNCTION RSA_GENERATE_KEYS(KEY_SIZE NUMBER) RETURN VARCHAR2
AS
LANGUAGE JAVA NAME 'com/dishtavar/crypto4ora/GenerateKey.generateRSAKeys (java.lang.Integer) return java.lang.String';

END CRYPTO;
/
```

## Usage

### Generating Keys
User can generate there own RSA public/private keys. But if they want to generate the key pair crypto4ora provided function `RSA_GENERATE_KEYS(KEY_SIZE)`.
```java
select CRYPTO.RSA_GENERATE_KEYS(1024) from dual;
```


## Encryption

Encryption can be done using public key by calling function `RSA_ENCRYPT(PLAIN_TEXT VARCHAR2,PUBLIC_KEY VARCHAR2)`.
```sql
select 
CRYPTO.RSA_ENCRYPT('This is Cipher text', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCg953Zj9l9EBvSLpaBzmmaviPfINooZYWm581n18bZuySRtalElh9469AdRUFOoV1Dxs/kVst1yzu6a7Drt1h3pgbXwwON5u/FtzOikCfurQ8lOWpHcdNv26b5nv6JkaHAzroK0pxbS8RjZLAUvZpHXr0JdcTcZNHkW62FC4LAFQIDAQAB') from dual;
```

### Decryption

Encryption can be done using public key by calling function `RSA_DECRYPT(ENCRYPTED_TEXT,PRIVATE_KEY)`.
```sql
select CRYPTO.RSA_DECRYPT('Pz5hrCs5ouRpqyiD6hb/gyyA9wxgKwzOnWruBRavYoxUbP4500ZhZb/VpqaHxQZzXiyQ4KUWKfZafAdj5w1la2m/8Fk7S76h0gTOS3kpzzR+jvTgRtTgL4fZ9xIfRT84oYE3HmQcyLYl9YyXzHJ881G0mIC/+phLTvSolwTkeWQ=','MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKD3ndmP2X0QG9IuloHOaZq+I98g2ihlhabnzWfXxtm7JJG1qUSWH3jr0B1FQU6hXUPGz+RWy3XLO7prsOu3WHemBtfDA43m78W3M6KQJ+6tDyU5akdx02/bpvme/omRocDOugrSnFtLxGNksBS9mkdevQl1xNxk0eRbrYULgsAVAgMBAAECgYBp3dGciIaTQWf62Zc1cmt0Ub/H/ZRd4z9+4Y/vzzPCedn4V1EngdDbN8Tdq3HjZC+x5u1OEWOpamwzFKb8sM5mf20HNwCQg5DMwpc+Bi4wKmxQOyaIGlma7kJTVt0GoRqVRWr0lFaMAjzsAU75XHIeMNQrqH34oZOnc9sR4fvUtQJBANfpN6VPlfYMHITFjJkrpbH4puUgcbDyJWv7LdGbpQAVS6q4KLpBJGUAw5f8bwjBwpOVLXHQ673f2OSTlQMDsRMCQQC+2smBrg5Otb+dieVNSaLO+uVRuVxlrzWRAzfubiCDQ3MvxxKX6b+tjr7ayjBSA3xJ82s2ii9QZBQUgCLU1Rc3AkEApihZZYPI0VBixh7NY7b+TecgcSY1uEmxWNc1XKDeKyE4WQpedW/aVUxwOc/PlzqdxjvAfF2efkH9RXL58xbtNwJBAIuXfpcI9ZIAQPw65aoX16rHu36AsuH0upsr3XbYuZMIfHFPi3vtDbMlmtZv19PElN2J2rrzaxPDfyRXbCJ7ERUCQFix34l063oCaTh6SolryYyLpt238Js56errsqNzLxUjGpXbOZ+iu5XS6xj+OgMkl1DRCvej2za6RyDNcJIuiAk=') from dual;
```


## Roadmap


