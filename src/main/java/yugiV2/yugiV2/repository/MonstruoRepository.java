package yugiV2.yugiV2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Types;
import java.util.Map;

@Repository
public class MonstruoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall crearMonstruoCall;
    private SimpleJdbcCall leerMonstruoCall;
    private SimpleJdbcCall actualizarMonstruoCall;
    private SimpleJdbcCall eliminarMonstruoCall;
    private SimpleJdbcCall listarMonstruosCall;

    @PostConstruct
    public void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        crearMonstruoCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("monstruos_pkg")
                .withProcedureName("crear_monstruo")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlParameter("p_nombre", Types.VARCHAR),
                        new SqlParameter("p_atributo", Types.VARCHAR),
                        new SqlParameter("p_nivel", Types.NUMERIC),
                        new SqlParameter("p_ataque", Types.NUMERIC),
                        new SqlParameter("p_defensa", Types.NUMERIC),
                        new SqlOutParameter("p_cursor", Types.REF_CURSOR)
                );

        leerMonstruoCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("monstruos_pkg")
                .withProcedureName("leer_monstruo")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlOutParameter("p_cursor", Types.REF_CURSOR)
                );

        listarMonstruosCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("monstruos_pkg")
                .withProcedureName("listar_monstruos")
                .declareParameters(
                        new SqlOutParameter("p_cursor", Types.REF_CURSOR)
                );

        actualizarMonstruoCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("monstruos_pkg")
                .withProcedureName("actualizar_monstruo")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlParameter("p_nombre", Types.VARCHAR),
                        new SqlParameter("p_atributo", Types.VARCHAR),
                        new SqlParameter("p_nivel", Types.NUMERIC),
                        new SqlParameter("p_ataque", Types.NUMERIC),
                        new SqlParameter("p_defensa", Types.NUMERIC),
                        new SqlOutParameter("p_cursor", Types.REF_CURSOR)
                );

        eliminarMonstruoCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("monstruos_pkg")
                .withProcedureName("eliminar_monstruo")
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlOutParameter("p_cursor", Types.REF_CURSOR)
                );
    }

    public Map<String, Object> crearMonstruo(int id, String nombre, String atributo, int nivel, int ataque, int defensa) {
        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_id", id)
                .addValue("p_nombre", nombre)
                .addValue("p_atributo", atributo)
                .addValue("p_nivel", nivel)
                .addValue("p_ataque", ataque)
                .addValue("p_defensa", defensa);
        return crearMonstruoCall.execute(in);
    }

    public Map<String, Object> leerMonstruo(int id) {
        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_id", id);
        return leerMonstruoCall.execute(in);
    }

    public Map<String, Object> listarMonstruos(){
        return listarMonstruosCall.execute();
    }

    public Map<String, Object> actualizarMonstruo(int id, String nombre, String atributo, int nivel, int ataque, int defensa) {
        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_id", id)
                .addValue("p_nombre", nombre)
                .addValue("p_atributo", atributo)
                .addValue("p_nivel", nivel)
                .addValue("p_ataque", ataque)
                .addValue("p_defensa", defensa);
        return actualizarMonstruoCall.execute(in);
    }

    public Map<String, Object> eliminarMonstruo(int id) {
        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("p_id", id);
        return eliminarMonstruoCall.execute(in);
    }
}
