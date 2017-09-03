# crypto4ora
crypto4ora provides simple and enhanced asymmetric cryptography capability in Oracle database. 

# Features

- Crypto4ora has implemented the asymmetric(RSA) cryptography.
- public/private key generator for RSA algorithm.
- Encryption using public key.
- Decryption using private key.
- Digital Signature using RSA algorithm(SHA1withRSA)
- Sign/Verify using RSA keys.

# Docs
- [Installation Guideline](#installation-guideline)
  - [Command Execution](#command-execution)
  - [SQL Script](#sql-script)
- [Usage](#usage)
  - [Generating Keys](#generating-keys)
  - [Encryption](#encryption)
  - [Decryption](#decryption)
  - [Sign](#sign)
  - [Verify Sign](#verify-sign)
- [Roadmap](#roadmap)

## Installation Guideline

Download the zip [crypto4ora](https://github.com/atul19971/crypto4ora/tree/master/zip) which contains `crypto4ora.jar` and `crypto4ora.sql`.


### Command Execution
Execute below command for loading the `crypto4ora.jar` in oracle DB.
```xml
loadjava -f -verbose -oracleresolver -resolve -oci8 -u user/password@service crypto4ora.jar
```

### SQL Script
Once loadjava command executed successfully run the script [crypto4ora.sql]( https://github.com/atul19971/crypto4ora/blob/master/sql/crypto4ora.sql) in your required schema.

## Usage

### Generating Keys
User can generate there own RSA public/private keys. But if they want to generate the key pair crypto4ora provided function `RSA_GENERATE_KEYS(KEY_SIZE)`.
```sql
select CRYPTO.RSA_GENERATE_KEYS(KEY_SIZE => 1024)
  from dual;
```

## Encryption

Encryption can be done using public key by calling function `RSA_ENCRYPT(PLAIN_TEXT VARCHAR2,PUBLIC_KEY VARCHAR2)`.
```sql
select CRYPTO.RSA_ENCRYPT(PLAIN_TEXT => 'This is Cipher text',
                          PUBLIC_KEY => 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCg953Zj9l9EBvSLpaBzmmaviPfINooZYWm581n18bZuySRtalElh9469AdRUFOoV1Dxs/kVst1yzu6a7Drt1h3pgbXwwON5u/FtzOikCfurQ8lOWpHcdNv26b5nv6JkaHAzroK0pxbS8RjZLAUvZpHXr0JdcTcZNHkW62FC4LAFQIDAQAB')
  from dual;
```

### Decryption

Encryption can be done using public key by calling function `RSA_DECRYPT(ENCRYPTED_TEXT,PRIVATE_KEY)`.
```sql
select CRYPTO.RSA_DECRYPT(ENCRYPTED_TEXT => 'Pz5hrCs5ouRpqyiD6hb/gyyA9wxgKwzOnWruBRavYoxUbP4500ZhZb/VpqaHxQZzXiyQ4KUWKfZafAdj5w1la2m/8Fk7S76h0gTOS3kpzzR+jvTgRtTgL4fZ9xIfRT84oYE3HmQcyLYl9YyXzHJ881G0mIC/+phLTvSolwTkeWQ=',
                          PRIVATE_KEY    => 'MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKD3ndmP2X0QG9IuloHOaZq+I98g2ihlhabnzWfXxtm7JJG1qUSWH3jr0B1FQU6hXUPGz+RWy3XLO7prsOu3WHemBtfDA43m78W3M6KQJ+6tDyU5akdx02/bpvme/omRocDOugrSnFtLxGNksBS9mkdevQl1xNxk0eRbrYULgsAVAgMBAAECgYBp3dGciIaTQWf62Zc1cmt0Ub/H/ZRd4z9+4Y/vzzPCedn4V1EngdDbN8Tdq3HjZC+x5u1OEWOpamwzFKb8sM5mf20HNwCQg5DMwpc+Bi4wKmxQOyaIGlma7kJTVt0GoRqVRWr0lFaMAjzsAU75XHIeMNQrqH34oZOnc9sR4fvUtQJBANfpN6VPlfYMHITFjJkrpbH4puUgcbDyJWv7LdGbpQAVS6q4KLpBJGUAw5f8bwjBwpOVLXHQ673f2OSTlQMDsRMCQQC+2smBrg5Otb+dieVNSaLO+uVRuVxlrzWRAzfubiCDQ3MvxxKX6b+tjr7ayjBSA3xJ82s2ii9QZBQUgCLU1Rc3AkEApihZZYPI0VBixh7NY7b+TecgcSY1uEmxWNc1XKDeKyE4WQpedW/aVUxwOc/PlzqdxjvAfF2efkH9RXL58xbtNwJBAIuXfpcI9ZIAQPw65aoX16rHu36AsuH0upsr3XbYuZMIfHFPi3vtDbMlmtZv19PElN2J2rrzaxPDfyRXbCJ7ERUCQFix34l063oCaTh6SolryYyLpt238Js56errsqNzLxUjGpXbOZ+iu5XS6xj+OgMkl1DRCvej2za6RyDNcJIuiAk=')
  from dual;
```
## Sign

Sign(RSA digital sign) the message using private key and by calling function `RSA_SIGN(PLAIN_MESSAGE VARCHAR2,PRIVATE_KEY VARCHAR2)`.
```sql
select CRYPTO.RSA_SIGN(PLAIN_TEXT => 'cipher',
                       PRIVATE_KEY =>  'MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAxiOZnCDcu4NS/VIs/umE6CpSAzHWPGaYslXaWmoyNBGUaL9dx961UnSMdOwEuKZ89jjrncQHM6iJn412AuFjNwIDAQABAkBDzECunMwK2afXCHxfCVax/nErqKzu5AhR1XCuyXG+QL+NrAjzF4MAyD4cL4W02LC5SdJVmEf2h55bBX1FEoRxAiEA4ztCZecHLKIlFCpKpfYnFzceWkiQOYcCUjcmknrsJgkCIQDfOW8Og7HlFchu6guSpuO/XGtLGQ6GPH5XnbFZ1p2PPwIhAMVdZEeUScP3tdL1BM9vpPZiFof+fN6qqFZ2tpcGE3TZAiEAqNbmO1I9IgtB2TLJohj9NmaO+PRw2fOzlE3AMTV1Yz0CIQDcm/cc4tnRcMS6l8vABGzWN+I8SbTqc1zFS4GQqeNG/g==') from dual;

```

### Verify Sign

Veification of signature can be done using public key and by calling function `RSA_VERIFY(MESSAGE_TO_VERIFY VARCHAR2,SIGNED_MESSAGE VARCHAR2, PUBLIC_KEY VARCHAR2)`. The function will return 0(false) and 1(true).
```sql
select CRYPTO.RSA_VERIFY(MESSAGE_TO_VERIFY => 'cipher',
                         SIGNED_MESSAGE => p3FbrxsRbDoLZuwtZvJpZ/f9HnbAHOmj4g1gtkhBudiofAYwAAIJCuVjbWlml8I1Nl07pJXVMTDKjmAL7gxAZA==, 
                         PUBLIC_KEY =>   'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMYjmZwg3LuDUv1SLP7phOgqUgMx1jxmmLJV2lpqMjQRlGi/XcfetVJ0jHTsBLimfPY4653EBzOoiZ+NdgLhYzcCAwEAAQ==') from dual;

```
## Roadmap

