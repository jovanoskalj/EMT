package mk.ukim.finki.listeners;

import mk.ukim.finki.events.HostChangedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class HostChangedListener {

    private final JdbcTemplate jdbcTemplate;

    public HostChangedListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener
    public void handleHostChanged(HostChangedEvent event) {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW host_count_by_country");
    }
}
