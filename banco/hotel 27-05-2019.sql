/*
Navicat MySQL Data Transfer

Source Server         : Localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : hotel

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-05-31 09:20:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cidade`
-- ----------------------------
DROP TABLE IF EXISTS `cidade`;
CREATE TABLE `cidade` (
  `codigo_cid` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_est_cid` int(11) NOT NULL,
  `descricao_cid` varchar(45) NOT NULL,
  PRIMARY KEY (`codigo_cid`),
  KEY `fk_cidade_estado1_idx` (`codigo_est_cid`),
  CONSTRAINT `fk_estado` FOREIGN KEY (`codigo_est_cid`) REFERENCES `estado` (`cod_estado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cidade
-- ----------------------------

-- ----------------------------
-- Table structure for `cliente`
-- ----------------------------
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `codigo_clie` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_tp_clie` int(11) NOT NULL,
  `codigo_gen_clie` int(11) DEFAULT NULL,
  `status_clie` tinyint(4) NOT NULL,
  `nomeRazaoSocial_clie` varchar(45) NOT NULL,
  `sobrenomeNomeFantasia_clie` varchar(45) NOT NULL,
  `dataNascimentoFundacao_clie` date DEFAULT NULL,
  `rgIe_clie` varchar(15) NOT NULL,
  `orgaoExpedidor_clie` varchar(30) DEFAULT NULL,
  `cpfCnpj_clie` varchar(18) NOT NULL,
  `dataInclusao_clie` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo_clie`),
  UNIQUE KEY `cpfCnpj_clie_UNIQUE` (`cpfCnpj_clie`),
  KEY `fk_cliente_tipoPessoa_idx` (`codigo_tp_clie`),
  KEY `fk_cliente_sexo1_idx` (`codigo_gen_clie`),
  CONSTRAINT `fk_cliente_sexo1` FOREIGN KEY (`codigo_gen_clie`) REFERENCES `genero` (`codigo_gen`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_tipoPessoa` FOREIGN KEY (`codigo_tp_clie`) REFERENCES `tipopessoa` (`codigo_tp`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cliente
-- ----------------------------
INSERT INTO `cliente` VALUES ('6', '1', '2', '1', 'Adão', 'Ferreira Ramos', '1987-11-01', '465656', 'DGPCGO', '111.111.111-11', '2019-05-23 16:49:49');
INSERT INTO `cliente` VALUES ('8', '2', '2', '1', 'Educar Distribuidora de Livros Ltda', 'Educar Livros', null, 'Isento', null, '65.546.546/5656-56', '2019-05-27 16:32:46');
INSERT INTO `cliente` VALUES ('9', '1', '2', '1', 'Matheus ', 'Cardoso', '1111-11-11', '1654656', '', '456.565.655-66', '2019-05-27 16:36:28');
INSERT INTO `cliente` VALUES ('10', '1', '2', '1', 'Cristiano', 'Santana', '1980-12-12', '4546565', '', '797.898.989-89', '2019-05-27 16:37:52');

-- ----------------------------
-- Table structure for `contatocliente`
-- ----------------------------
DROP TABLE IF EXISTS `contatocliente`;
CREATE TABLE `contatocliente` (
  `codigo_cc` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_clie_cc` int(11) NOT NULL,
  `telefone_cc` varchar(15) NOT NULL,
  `celular_cc` varchar(15) DEFAULT NULL,
  `email_cc` varchar(45) DEFAULT NULL,
  `site_cc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo_cc`),
  KEY `fk_contatoCliente_cliente1_idx` (`codigo_clie_cc`),
  CONSTRAINT `fk_contatoCliente_cliente1` FOREIGN KEY (`codigo_clie_cc`) REFERENCES `cliente` (`codigo_clie`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contatocliente
-- ----------------------------
INSERT INTO `contatocliente` VALUES ('5', '6', '(62) 3063-9026', '(62) 98243-1951', 'ramos.adao@gmail.com', '');
INSERT INTO `contatocliente` VALUES ('7', '8', '(62) 4656-4546', '(65) 46564-5645', 'contato@educarlivros.com.br', '');
INSERT INTO `contatocliente` VALUES ('8', '9', '(46) 5465-6564', '(65) 46456-5656', '', '');
INSERT INTO `contatocliente` VALUES ('9', '10', '(79) 8989-8988', '(77) 89879-8798', '', '');

-- ----------------------------
-- Table structure for `endereco`
-- ----------------------------
DROP TABLE IF EXISTS `endereco`;
CREATE TABLE `endereco` (
  `codigo_en` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_clie` int(11) NOT NULL,
  `codigo_tip` int(11) NOT NULL,
  `logradouro_en` varchar(45) NOT NULL,
  `numero_en` varchar(10) NOT NULL,
  `bairro_en` varchar(45) NOT NULL,
  `complemento_en` varchar(45) DEFAULT NULL,
  `cep_en` varchar(45) NOT NULL,
  `cidade_en` varchar(45) NOT NULL,
  `estado_en` varchar(2) NOT NULL,
  PRIMARY KEY (`codigo_en`),
  KEY `fk_clienteEndereco_cidade1_idx` (`cidade_en`),
  KEY `fk_clienteEndereco_cliente1_idx` (`codigo_clie`),
  KEY `fk_clienteEndereco_tipoEndereco1_idx` (`codigo_tip`),
  CONSTRAINT `fk_clienteEndereco_cliente1` FOREIGN KEY (`codigo_clie`) REFERENCES `cliente` (`codigo_clie`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_clienteEndereco_tipoEndereco1` FOREIGN KEY (`codigo_tip`) REFERENCES `tipoendereco` (`codigo_te`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of endereco
-- ----------------------------
INSERT INTO `endereco` VALUES ('3', '6', '1', 'Rua C 15', 's/n', 'Jardim das Cascatas', 'Qd. 14 Lt. 11', '74960-450', 'Aparecida de Goiânia', 'GO');
INSERT INTO `endereco` VALUES ('10', '8', '2', 'Rua 70', '647', 'Setor Central', '', '74055-120', 'Goiânia', 'GO');
INSERT INTO `endereco` VALUES ('11', '9', '1', 'Rua B', '68', 'Jardim Bela Morada', '', '74564-654', 'Aparecida de Goiânia', 'GO');
INSERT INTO `endereco` VALUES ('13', '10', '1', 'Rua C', '100', 'Veija Jardim', '', '79879-898', 'Aparecida de Goiânia', 'GO');
INSERT INTO `endereco` VALUES ('14', '6', '2', 'Rua 70', '647', 'Setor Central', '', '74055-120', 'Goiânia', 'GO');

-- ----------------------------
-- Table structure for `estado`
-- ----------------------------
DROP TABLE IF EXISTS `estado`;
CREATE TABLE `estado` (
  `cod_estado` int(11) NOT NULL AUTO_INCREMENT,
  `uf` varchar(2) NOT NULL,
  `nome` varchar(72) NOT NULL,
  PRIMARY KEY (`cod_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of estado
-- ----------------------------
INSERT INTO `estado` VALUES ('1', 'AC', 'ACRE');
INSERT INTO `estado` VALUES ('2', 'AL', 'ALAGOAS');
INSERT INTO `estado` VALUES ('3', 'AP', 'AMAPÁ');
INSERT INTO `estado` VALUES ('4', 'AM', 'AMAZONAS');
INSERT INTO `estado` VALUES ('5', 'BA', 'BAHIA');
INSERT INTO `estado` VALUES ('6', 'CE', 'CEARÁ');
INSERT INTO `estado` VALUES ('7', 'DF', 'DISTRITO FEDERAL');
INSERT INTO `estado` VALUES ('8', 'ES', 'ESPÍRITO SANTO');
INSERT INTO `estado` VALUES ('9', 'RR', 'RORAIMA');
INSERT INTO `estado` VALUES ('10', 'GO', 'GOIÁS');
INSERT INTO `estado` VALUES ('11', 'MA', 'MARANHÃO');
INSERT INTO `estado` VALUES ('12', 'MT', 'MATO GROSSO');
INSERT INTO `estado` VALUES ('13', 'MS', 'MATO GROSSO DO SUL');
INSERT INTO `estado` VALUES ('14', 'MG', 'MINAS GERAIS');
INSERT INTO `estado` VALUES ('15', 'PA', 'PARÁ');
INSERT INTO `estado` VALUES ('16', 'PB', 'PARAÍBA');
INSERT INTO `estado` VALUES ('17', 'PR', 'PARANÁ');
INSERT INTO `estado` VALUES ('18', 'PE', 'PERNAMBUCO');
INSERT INTO `estado` VALUES ('19', 'PI', 'PIAUÍ');
INSERT INTO `estado` VALUES ('20', 'RJ', 'RIO DE JANEIRO');
INSERT INTO `estado` VALUES ('21', 'RN', 'RIO GRANDE DO NORTE');
INSERT INTO `estado` VALUES ('22', 'RS', 'RIO GRANDE DO SUL');
INSERT INTO `estado` VALUES ('23', 'RO', 'RONDÔNIA');
INSERT INTO `estado` VALUES ('24', 'TO', 'TOCANTINS');
INSERT INTO `estado` VALUES ('25', 'SC', 'SANTA CATARINA');
INSERT INTO `estado` VALUES ('26', 'SP', 'SÃO PAULO');
INSERT INTO `estado` VALUES ('27', 'SE', 'SERGIPE');

-- ----------------------------
-- Table structure for `genero`
-- ----------------------------
DROP TABLE IF EXISTS `genero`;
CREATE TABLE `genero` (
  `codigo_gen` int(11) NOT NULL,
  `descricao_gen` varchar(15) NOT NULL,
  PRIMARY KEY (`codigo_gen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of genero
-- ----------------------------
INSERT INTO `genero` VALUES ('1', 'Masculino');
INSERT INTO `genero` VALUES ('2', 'Feminino');
INSERT INTO `genero` VALUES ('3', 'Não declarado');

-- ----------------------------
-- Table structure for `pais`
-- ----------------------------
DROP TABLE IF EXISTS `pais`;
CREATE TABLE `pais` (
  `codigo_pai` int(11) NOT NULL AUTO_INCREMENT,
  `sigla_pai` varchar(3) NOT NULL,
  `descricao_pai` varchar(45) NOT NULL,
  PRIMARY KEY (`codigo_pai`),
  UNIQUE KEY `sigla_pai_UNIQUE` (`sigla_pai`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pais
-- ----------------------------
INSERT INTO `pais` VALUES ('1', 'BRA', 'BRASIL');

-- ----------------------------
-- Table structure for `tipoendereco`
-- ----------------------------
DROP TABLE IF EXISTS `tipoendereco`;
CREATE TABLE `tipoendereco` (
  `codigo_te` int(11) NOT NULL AUTO_INCREMENT,
  `descricao_te` varchar(45) NOT NULL,
  PRIMARY KEY (`codigo_te`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tipoendereco
-- ----------------------------
INSERT INTO `tipoendereco` VALUES ('1', 'Residencial');
INSERT INTO `tipoendereco` VALUES ('2', 'Comercial');

-- ----------------------------
-- Table structure for `tipopessoa`
-- ----------------------------
DROP TABLE IF EXISTS `tipopessoa`;
CREATE TABLE `tipopessoa` (
  `codigo_tp` int(11) NOT NULL AUTO_INCREMENT,
  `descricao_tp` varchar(45) NOT NULL,
  PRIMARY KEY (`codigo_tp`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tipopessoa
-- ----------------------------
INSERT INTO `tipopessoa` VALUES ('1', 'Física');
INSERT INTO `tipopessoa` VALUES ('2', 'Jurídica');

-- ----------------------------
-- Table structure for `usuario`
-- ----------------------------
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `codigo_usu` int(11) NOT NULL AUTO_INCREMENT,
  `nome_usu` varchar(45) NOT NULL,
  `login_usu` varchar(45) NOT NULL,
  `senha_usu` varchar(45) NOT NULL,
  `data_inclusao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codigo_usu`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usuario
-- ----------------------------
INSERT INTO `usuario` VALUES ('1', 'Adao Ferreira', 'adao', '291410383094904320441589364556562936138', '2019-05-14 08:38:44');
