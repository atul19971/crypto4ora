--+++++++++++++++++++++++++++++++++++++++++++++++++++++++
--START : PLEASE DO NOT MAKE ANY CHANGES TO THIS SECTION.
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++
SET define on
SET echo on
SET linesize 2048
SET escape off
SET timing on
SET trimspool on
SET serveroutput on
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--END : PLEASE DO NOT MAKE ANY CHANGES TO THIS SECTION.							 
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++

CREATE OR REPLACE PACKAGE CRYPTO AS 
FUNCTION RSA_ENCRYPT(PLAIN_TEXT VARCHAR2,PRIVATE_KEY VARCHAR2) RETURN VARCHAR2
AS
LANGUAGE JAVA NAME 'com/dishtavar/crypto4ora/RSAUtil.encrypt (java.lang.String,java.lang.String) return java.lang.String';


FUNCTION RSA_DECRYPT(ENCRYPTED_TEXT VARCHAR2,PUBLIC_KEY VARCHAR2) RETURN VARCHAR2
AS
LANGUAGE JAVA NAME 'com/dishtavar/crypto4ora/RSAUtil.decrypt (java.lang.String,java.lang.String) return java.lang.String';


FUNCTION RSA_SIGN(HASH_MESSAGE VARCHAR2,PUBLIC_KEY VARCHAR2) RETURN VARCHAR2
AS
LANGUAGE JAVA NAME 'com/dishtavar/crypto4ora/RSAUtil.sign (java.lang.String,java.lang.String) return java.lang.String';


FUNCTION RSA_VERIFY(PLAIN_HASH VARCHAR2,SIGNNED_HASH VARCHAR2,PRIVATE_KEY VARCHAR2) RETURN BOOLEAN
AS
LANGUAGE JAVA NAME 'com/dishtavar/crypto4ora/RSAUtil.verify (java.lang.String,java.lang.String,java.lang.String) return java.lang.Boolean';

FUNCTION RSA_GENERATE_KEYS(KEY_SIZE NUMBER) RETURN VARCHAR2
AS
LANGUAGE JAVA NAME 'com/dishtavar/crypto4ora/GenerateKey.generateRSAKeys (java.lang.Integer) return java.lang.String';

END CRYPTO;
/
