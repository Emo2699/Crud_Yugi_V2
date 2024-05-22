CREATE OR REPLACE PACKAGE monstruos_pkg AS
    -- Definición del tipo RECORD basado en la tabla MONSTRUO
    TYPE monstruo_rec IS RECORD (
        idcard   MONSTRUO.IDCARD%TYPE,
        nombre   MONSTRUO.NOMBRE%TYPE,
        atributo MONSTRUO.ATRIBUTO%TYPE,
        nivel    MONSTRUO.NIVEL%TYPE,
        ataque   MONSTRUO.ATAQUE%TYPE,
        defensa  MONSTRUO.DEFENSA%TYPE
    );

    -- Declaración del tipo SYS_REFCURSOR
    TYPE monstruo_refcursor IS REF CURSOR RETURN monstruo_rec;

    PROCEDURE crear_monstruo(p_id IN NUMBER, p_nombre IN VARCHAR2, p_atributo IN VARCHAR2, p_nivel IN NUMBER, 
                             p_ataque IN NUMBER, p_defensa IN NUMBER, p_cursor OUT monstruo_refcursor);

    PROCEDURE leer_monstruo(p_id IN NUMBER, p_cursor OUT monstruo_refcursor);

    PROCEDURE listar_monstruos(p_cursor OUT monstruo_refcursor);

    PROCEDURE actualizar_monstruo(p_id IN NUMBER, p_nombre IN VARCHAR2, p_atributo IN VARCHAR2, p_nivel IN NUMBER,
                                  p_ataque IN NUMBER, p_defensa IN NUMBER, p_cursor OUT monstruo_refcursor);

    PROCEDURE eliminar_monstruo(p_id IN NUMBER, p_cursor OUT monstruo_refcursor);
END monstruos_pkg;
/

CREATE OR REPLACE PACKAGE BODY monstruos_pkg AS
    -- Procedimiento para crear un monstruo
    PROCEDURE crear_monstruo(p_id IN NUMBER, p_nombre IN VARCHAR2, p_atributo IN VARCHAR2, p_nivel IN NUMBER, 
                             p_ataque IN NUMBER, p_defensa IN NUMBER, p_cursor OUT monstruo_refcursor) IS
    BEGIN
        INSERT INTO monstruo (idcard, nombre, atributo, nivel, ataque, defensa)
        VALUES (p_id, p_nombre, p_atributo, p_nivel, p_ataque, p_defensa);
        COMMIT;
        OPEN p_cursor FOR SELECT * FROM monstruo WHERE idcard = p_id;
    END crear_monstruo;

    -- Procedimiento para leer un monstruo
    PROCEDURE leer_monstruo(p_id IN NUMBER, p_cursor OUT monstruo_refcursor) IS
    BEGIN
        OPEN p_cursor FOR SELECT * FROM monstruo WHERE idcard = p_id;
    END leer_monstruo;

    -- Procedimiento para listar los monstruos
    PROCEDURE listar_monstruos(p_cursor OUT monstruo_refcursor) IS
    BEGIN
        OPEN p_cursor FOR SELECT * FROM monstruo;
    END listar_monstruos;

    -- Procedimiento para actualizar un monstruo
    PROCEDURE actualizar_monstruo(p_id IN NUMBER, p_nombre IN VARCHAR2, p_atributo IN VARCHAR2, p_nivel IN NUMBER,
                                  p_ataque IN NUMBER, p_defensa IN NUMBER, p_cursor OUT monstruo_refcursor) IS
    BEGIN
        UPDATE monstruo
        SET nombre = p_nombre, atributo = p_atributo, nivel = p_nivel, ataque = p_ataque, defensa = p_defensa
        WHERE idcard = p_id;
        COMMIT;
        OPEN p_cursor FOR SELECT * FROM monstruo WHERE idcard = p_id;
    END actualizar_monstruo;

    -- Procedimiento para eliminar un monstruo
    PROCEDURE eliminar_monstruo(p_id IN NUMBER, p_cursor OUT monstruo_refcursor) IS
    BEGIN
        DELETE FROM monstruo WHERE idcard = p_id;
        COMMIT;
        OPEN p_cursor FOR SELECT * FROM monstruo;
    END eliminar_monstruo;
END monstruos_pkg;
/
