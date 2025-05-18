CREATE MATERIALIZED VIEW display_accommodations_by_host AS
SELECT
    h.name,
    h.surname,
    COUNT(a.id) AS num_accommodations
FROM
    host h
        LEFT JOIN
    accommodation a ON a.host_id = h.id
GROUP BY
    h.name, h.surname;

CREATE MATERIALIZED VIEW host_count_by_country AS
SELECT
    country.name AS country,
    COUNT(host.id) AS host_count
FROM host
         JOIN country ON host.country_id = country.id
GROUP BY country.name;

select * from country;
