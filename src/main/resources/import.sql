INSERT INTO company(name) VALUES ('Google');
INSERT INTO company(name) VALUES ('Microsoft');

INSERT INTO project(company_id, name) SELECT id, 'Adwords'       FROM company WHERE name = 'Google';
INSERT INTO project(company_id, name) SELECT id, 'Gmail'         FROM company WHERE name = 'Google';
INSERT INTO project(company_id, name) SELECT id, 'Search'        FROM company WHERE name = 'Google';
INSERT INTO project(company_id, name) SELECT id, 'Office'        FROM company WHERE name = 'Microsoft';
INSERT INTO project(company_id, name) SELECT id, 'Visual Studio' FROM company WHERE name = 'Microsoft';
INSERT INTO project(company_id, name) SELECT id, 'Windows'       FROM company WHERE name = 'Microsoft';
