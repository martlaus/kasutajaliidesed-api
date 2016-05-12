USE kasutajaliidesedApi;

-- Add Users
INSERT INTO User (id, code, name, password, role, created)
VALUES (1, '123456', 'Admin', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'ADMIN', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (2, '123875', 'Mart Laus', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (3, '123123', 'Madis', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'ADMIN', NOW());

-- parool on 123123
INSERT INTO User (id, code, name, password, role, created)
VALUES (4, '123124', 'Peeter', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (5, '123125', 'Ats', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (6, '123126', 'Mats', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (7, '123127', 'Jaan', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());


INSERT INTO User (id, code, name, password, role, created)
VALUES (8, '2123124', 'Volli', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (9, '2123125', 'Maali', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (10, '2123126', 'Mari', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (11, '2123127', 'Aare', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (12, '3123124', 'Arnold', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (13, '3123125', 'Maarja', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (14, '3123126', 'Ken', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (15, '3123127', 'Alar', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (16, '4123124', 'Kevin', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (17, '4123125', 'Kertu', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (18, '4123126', 'Oskar', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());
INSERT INTO User (id, code, name, password, role, created)
VALUES (19, '4123127', 'Laur', '$2a$10$qbbOehDfDbD0NQx2fC6Rhu7FpqtgY.jD3dv3K3TTx.T39tL.SNVom', 'USER', NOW());



INSERT INTO Subject (id, user_id, name) VALUES (1, 1, 'Andmebaasid 1');


INSERT INTO Homework (id, name, user, subject_id, added) VALUES (1, 'KT1', 1, 1, NOW());
INSERT INTO Homework (id, name, user, subject_id, added) VALUES (2, 'KT2', 1, 1, NOW());
INSERT INTO Homework (id, name, user, subject_id, added) VALUES (3, 'KT3', 1, 1, NOW());
INSERT INTO Homework (id, name, user, subject_id, added) VALUES (4, 'Test', 1, 1, NOW());
INSERT INTO Homework (id, name, user, subject_id, added) VALUES (5, 'Eksam', 1, 1, NOW());
INSERT INTO Homework (id, name, user, subject_id, added) VALUES (6, 'Eksam2', 1, 1, NOW());

INSERT INTO Homework (id, name, user, subject_id, added) VALUES (11, 'KT1', 2, 1, NOW());
INSERT INTO Homework (id, name, user, subject_id, added) VALUES (12, 'KT2', 2, 1, NOW());
INSERT INTO Homework (id, name, user, subject_id, added) VALUES (13, 'KT3', 2, 1, NOW());
INSERT INTO Homework (id, name, user, subject_id, added) VALUES (14, 'Test', 2, 1, NOW());
INSERT INTO Homework (id, name, user, subject_id, added) VALUES (15, 'Eksam', 2, 1, NOW());
INSERT INTO Homework (id, name, user, subject_id, added) VALUES (16, 'Eksam2', 2, 1, NOW());

INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (1, 2, 1, 1);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (2, 2, 2, 2);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (3, 2, 3, 3);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (4, 2, 4, 4);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (5, 2, 5, 5);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (6, 2, 1, 6);

INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (21, 3, 1, 1);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (22, 3, 2, 2);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (23, 3, 3, 3);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (24, 3, 4, 4);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (25, 3, 5, 5);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (26, 3, 1, 6);

INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (31, 4, 1, 1);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (32, 4, 2, 2);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (33, 4, 3, 3);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (34, 4, 4, 4);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (35, 4, 5, 5);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (36, 4, 1, 6);

INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (41, 5, 1, 1);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (42, 5, 2, 2);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (43, 5, 3, 3);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (44, 5, 4, 4);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (45, 5, 5, 5);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (46, 5, 1, 6);

INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (51, 6, 1, 1);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (52, 6, 2, 2);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (53, 6, 3, 3);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (54, 6, 4, 4);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (55, 6, 5, 5);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (56, 6, 1, 6);

INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (61, 7, 1, 1);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (62, 7, 2, 2);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (63, 7, 3, 3);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (64, 7, 4, 4);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (65, 7, 5, 5);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (66, 7, 1, 6);

INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (71, 8, 1, 1);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (72, 8, 2, 2);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (73, 8, 3, 3);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (74, 8, 4, 4);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (75, 8, 5, 5);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (76, 8, 1, 6);

INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (81, 9, 1, 1);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (82, 9, 2, 2);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (83, 9, 3, 3);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (84, 9, 4, 4);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (85, 9, 5, 5);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (86, 9, 1, 6);

INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (91, 10, 1, 1);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (92, 10, 2, 2);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (93, 10, 3, 3);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (94, 10, 4, 4);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (95, 10, 5, 5);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (96, 10, 1, 6);

INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (101, 11, 1, 1);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (102, 11, 2, 2);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (103, 11, 3, 3);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (104, 11, 4, 4);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (105, 11, 5, 5);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (106, 11, 1, 6);

INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (111, 12, 1, 1);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (112, 12, 2, 2);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (113, 12, 3, 3);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (114, 12, 4, 4);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (115, 12, 5, 5);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (116, 12, 1, 6);

INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (121, 13, 1, 1);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (122, 13, 2, 2);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (123, 13, 3, 3);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (124, 13, 4, 4);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (125, 13, 5, 5);
INSERT INTO Grade (id, user_id, grade, homework_id) VALUES (126, 13, 1, 6);


INSERT INTO AuthenticatedUser (id, user_id, token) VALUES (1, 1, 'superUniqueToken');


