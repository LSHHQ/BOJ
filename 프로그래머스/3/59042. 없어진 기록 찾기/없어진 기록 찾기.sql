-- 코드를 입력하세요
# SELECT * FROM ANIMAL_INS;

# SELECT * FROM ANIMAL_OUTS;

SELECT EX.ANIMAL_ID, EX.NAME 
FROM ANIMAL_INS AS EN
RIGHT OUTER JOIN ANIMAL_OUTS AS EX
ON EN.ANIMAL_ID = EX.ANIMAL_ID
WHERE INTAKE_CONDITION IS NULL;