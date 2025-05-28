-- A quantidade de horas que cada professor tem comprometido em aulas - Então faça uma consulta SQL que traga essa informação.

SELECT 
    p.id AS professor_id,
    p.name AS professor_nome,
    t.name AS titulo,
    SEC_TO_TIME(SUM(TIME_TO_SEC(TIMEDIFF(cs.end_time, cs.start_time)))) AS total_horas_aula,
    COUNT(DISTINCT cs.id) AS total_aulas
FROM 
    PROFESSOR p
JOIN 
    TITLE t ON p.title_id = t.id
JOIN 
    CLASS c ON p.id = c.professor_id  -- Assumindo que CLASS tem professor_id
JOIN 
    CLASS_SCHEDULE cs ON c.id = cs.class_id
GROUP BY 
    p.id, p.name, t.name
ORDER BY 
    total_horas_aula DESC;


-- Lista de salas com horários livres e ocupados - Pode usar SQL e a linguagem de programação que achar melhor.	
	
SELECT 
    r.id AS sala_id,
    r.nome AS sala_nome,
    d.nome AS departamento,
    cs.dia_semana,
    cs.hora_inicio,
    cs.hora_fim,
    s.codigo AS codigo_disciplina,
    s.nome AS nome_disciplina,
    CONCAT(p.nome, ' (', t.nome, ')') AS professor
FROM 
    SALA r
JOIN 
    CLASS_SCHEDULE cs ON r.id = cs.sala_id
JOIN 
    CLASS c ON cs.class_id = c.id
JOIN 
    DISCIPLINA s ON c.disciplina_id = s.id
JOIN 
    PROFESSOR p ON c.professor_id = p.id
JOIN 
    TITULO t ON p.titulo_id = t.id
JOIN 
    DEPARTAMENTO d ON s.departamento_id = d.id
ORDER BY 
    r.id, cs.dia_semana, cs.hora_inicio;	
	