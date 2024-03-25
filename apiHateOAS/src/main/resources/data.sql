INSERT INTO client (id,nom, prenom, adresse) VALUES (1,'DUPONT', 'Jean', '1 rue de la paix');
INSERT INTO client (id,nom, prenom, adresse) VALUES (2,'DURAND', 'Paul', '2 rue de la paix');
INSERT INTO client (id,nom, prenom, adresse) VALUES (3,'DUPUIS', 'Marie', '3 rue de la paix');
INSERT INTO client (id,nom, prenom, adresse) VALUES (4,'DUPOND', 'Sophie', '4 rue de la paix');
INSERT INTO client (id,nom, prenom, adresse) VALUES (5,'DUPOND', 'Sophie', '5 rue de la paix');

INSERT INTO demande_credit (id,nom, prenom, adresse, date_naissance, emploi_actuel, montant_credit_demande, duree_credit_demande, statut, client_id, created_at, updated_at)
VALUES (1,'Dupont', 'Jean', '123 Rue de Paris', '1980-01-01', 'Ingénieur', 50000, 5, 'Etat initial', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO demande_credit (id,nom, prenom, adresse, date_naissance, emploi_actuel, montant_credit_demande, duree_credit_demande, statut, client_id, created_at, updated_at)
VALUES (2,'Martin', 'Marie', '456 Rue de Lyon', '1985-02-02', 'Architecte', 6000, 10, 'Etat initial', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO demande_credit (id,nom, prenom, adresse, date_naissance, emploi_actuel, montant_credit_demande, duree_credit_demande, statut, client_id, created_at, updated_at)
VALUES (3,'Durand', 'Paul', '789 Rue de Marseille', '1990-03-03', 'Médecin', 345, 15, 'Etat initial', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO demande_credit (id,nom, prenom, adresse, date_naissance, emploi_actuel, montant_credit_demande, duree_credit_demande, statut, client_id, created_at, updated_at)
VALUES (4,'Dupuis', 'Sophie', '1011 Rue de Toulouse', '1995-04-04', 'Avocat', 70, 20, 'Etat initial', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO demande_credit (id,nom, prenom, adresse, date_naissance, emploi_actuel, montant_credit_demande, duree_credit_demande, statut, client_id, created_at, updated_at)
VALUES (5,'Dupond', 'Sophie', '1213 Rue de Nice', '2000-05-05', 'Policier', 74643, 25, 'Etat initial', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO DEMANDE_CREDIT_REVENUS_TROIS_DERNIERES_ANNEES (REVENUS_TROIS_DERNIERES_ANNEES , demande_credit_id) VALUES (50000, 1);
INSERT INTO DEMANDE_CREDIT_REVENUS_TROIS_DERNIERES_ANNEES (REVENUS_TROIS_DERNIERES_ANNEES , demande_credit_id) VALUES (60000, 1);
INSERT INTO DEMANDE_CREDIT_REVENUS_TROIS_DERNIERES_ANNEES (REVENUS_TROIS_DERNIERES_ANNEES , demande_credit_id) VALUES (70000, 1);

INSERT INTO DEMANDE_CREDIT_REVENUS_TROIS_DERNIERES_ANNEES (REVENUS_TROIS_DERNIERES_ANNEES , demande_credit_id) VALUES (800, 2);
INSERT INTO DEMANDE_CREDIT_REVENUS_TROIS_DERNIERES_ANNEES (REVENUS_TROIS_DERNIERES_ANNEES , demande_credit_id) VALUES (9000, 2);
INSERT INTO DEMANDE_CREDIT_REVENUS_TROIS_DERNIERES_ANNEES (REVENUS_TROIS_DERNIERES_ANNEES , demande_credit_id) VALUES (10000, 2);

INSERT INTO DEMANDE_CREDIT_REVENUS_TROIS_DERNIERES_ANNEES (REVENUS_TROIS_DERNIERES_ANNEES , demande_credit_id) VALUES (110000, 3);
INSERT INTO DEMANDE_CREDIT_REVENUS_TROIS_DERNIERES_ANNEES (REVENUS_TROIS_DERNIERES_ANNEES , demande_credit_id) VALUES (120000, 3);
INSERT INTO DEMANDE_CREDIT_REVENUS_TROIS_DERNIERES_ANNEES (REVENUS_TROIS_DERNIERES_ANNEES , demande_credit_id) VALUES (130000, 3);

INSERT INTO DEMANDE_CREDIT_REVENUS_TROIS_DERNIERES_ANNEES (REVENUS_TROIS_DERNIERES_ANNEES , demande_credit_id) VALUES (14000, 4);
INSERT INTO DEMANDE_CREDIT_REVENUS_TROIS_DERNIERES_ANNEES (REVENUS_TROIS_DERNIERES_ANNEES , demande_credit_id) VALUES (1500, 4);
INSERT INTO DEMANDE_CREDIT_REVENUS_TROIS_DERNIERES_ANNEES (REVENUS_TROIS_DERNIERES_ANNEES , demande_credit_id) VALUES (16000, 4);

INSERT INTO DEMANDE_CREDIT_REVENUS_TROIS_DERNIERES_ANNEES (REVENUS_TROIS_DERNIERES_ANNEES , demande_credit_id) VALUES (17000, 5);
INSERT INTO DEMANDE_CREDIT_REVENUS_TROIS_DERNIERES_ANNEES (REVENUS_TROIS_DERNIERES_ANNEES , demande_credit_id) VALUES (180000, 5);
INSERT INTO DEMANDE_CREDIT_REVENUS_TROIS_DERNIERES_ANNEES (REVENUS_TROIS_DERNIERES_ANNEES , demande_credit_id) VALUES (10000, 5);
