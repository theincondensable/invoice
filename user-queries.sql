/* you absolutely have to change this line and address your own db schema. */
use invoice_db;

/* the encoded Password for "12345678" is
   $e0801$QqY4s3ayvvG95vxajDzgyw1FvA+yi8q/Vzo8cwSQUKt+prr85ZuDJ27amNF9gDRonkmUohW16HDytBoljV6Ivg==$R/0QfuSx4poaSUDtc8IU0Ac6AiHSYpnv+Ws9rr6wcqM=
   */

INSERT INTO t_user(password, role, username)
VALUES ('$e0801$QqY4s3ayvvG95vxajDzgyw1FvA+yi8q/Vzo8cwSQUKt+prr85ZuDJ27amNF9gDRonkmUohW16HDytBoljV6Ivg==$R/0QfuSx4poaSUDtc8IU0Ac6AiHSYpnv+Ws9rr6wcqM=',
        'ADMIN', 'user_admin');

INSERT INTO t_user(password, role, username)
VALUES ('$e0801$QqY4s3ayvvG95vxajDzgyw1FvA+yi8q/Vzo8cwSQUKt+prr85ZuDJ27amNF9gDRonkmUohW16HDytBoljV6Ivg==$R/0QfuSx4poaSUDtc8IU0Ac6AiHSYpnv+Ws9rr6wcqM=',
        'CUSTOMER', 'user_customer');