-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.6.16-MariaDB-0ubuntu0.22.04.1 - Ubuntu 22.04
-- OS do Servidor:               debian-linux-gnu
-- HeidiSQL Versão:              12.4.0.6659
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Copiando dados para a tabela cifep_equipamento.data: ~3 rows (aproximadamente)
INSERT INTO `data` (`agenda_id`, `data_devolucao`, `data_finalizada`, `data_retirada`, `hora_devolucao`, `hora_finalizada`, `hora_retirada`, `reserva_id`) VALUES
	(1, '2024-03-25', NULL, '2024-03-24', '09:00:00.000000', NULL, '10:00:00.000000', 1),
	(2, '2024-03-25', NULL, '2024-03-24', '09:00:00.000000', NULL, '10:00:00.000000', 2),
	(3, '2024-03-25', NULL, '2024-03-24', '09:00:00.000000', NULL, '10:00:00.000000', 3),
	(4, '2024-03-18', NULL, '2024-03-18', '14:00:00.000000', NULL, '13:00:00.000000', 4),
	(5, '2024-12-25', NULL, '2024-03-25', '14:00:00.000000', NULL, '13:00:00.000000', 5),
	(6, '2024-03-28', NULL, '2024-03-27', '09:00:00.000000', NULL, '10:00:00.000000', 6);

-- Copiando dados para a tabela cifep_equipamento.equipamento: ~3 rows (aproximadamente)
INSERT INTO `equipamento` (`id`, `descricao`, `quantidade`, `reserva_id`) VALUES
	(1, 'NOTEBOOK', 1, 1),
	(2, 'DATASHOW', 1, 2),
	(3, 'MICROOFONE', 1, 3),
	(4, 'NOTEBOOK', 1, 4),
	(5, 'NOTEBOOK', 1, 5),
	(6, 'NOTEBOOK', 1, 6);

-- Copiando dados para a tabela cifep_equipamento.reserva: ~6 rows (aproximadamente)
INSERT INTO `reserva` (`id`, `recorrencia_de_toda`, `responsavel`, `setor`, `status`, `tipo`) VALUES
	(1, '', 'Gleice', 'Enfermagem', 'ATIVA', 'EVENTUAL'),
	(2, '', 'Gerson', 'OPME', 'ATIVA', 'EVENTUAL'),
	(3, '', 'Patrícia', 'Diretoria', 'ATIVA', 'EVENTUAL'),
	(4, 'segunda-feira', 'Jefersom', 'Endoscopia', 'ATIVA', 'ANUAL'),
	(5, 'segunda-feira', 'Jefersom', 'Neurologia', 'ATIVA', 'ANUAL'),
	(6, '', 'Thyerri', 'Clinica Geral', 'ATIVA', 'EVENTUAL');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
