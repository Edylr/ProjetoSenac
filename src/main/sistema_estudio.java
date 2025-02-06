package main;

import java.sql.*;
import java.util.Scanner;

public class sistema_estudio {

	private static Connection conectar() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/sistema_estudio";
		String usuario = "root";
		String senha = "123456";
		return DriverManager.getConnection(url, usuario, senha);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean sair = false;

		while (!sair) {
			System.out.println("Menu Principal:");
			System.out.println("1. Gerenciar Clientes");
			System.out.println("2. Gerenciar Equipamentos");
			System.out.println("3. Gerenciar Locação");
			System.out.println("4. Sair");
			System.out.print("Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				menuClientes(scanner);
				break;
			case 2:
				menuEquipamentos(scanner);
				break;
			case 3:
				menuLocacao(scanner);
				break;
			case 4:
				sair = true;
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida! Tente novamente.");
			}
		}
		scanner.close();
	}

	private static void menuClientes(Scanner scanner) {
		boolean voltar = false;
		while (!voltar) {
			System.out.println("\nMenu Clientes:");
			System.out.println("1. Listar Clientes");
			System.out.println("2. Adicionar Cliente");
			System.out.println("3. Editar Cliente");
			System.out.println("4. Excluir Cliente");
			System.out.println("5. Voltar");
			System.out.println("Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				listarClientes();
				break;
			case 2:
				adicionarCliente(scanner);
				break;
			case 3:
				editarCliente(scanner);
				break;
			case 4:
				excluirCliente(scanner);
				break;
			case 5:
				voltar = true;
				break;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}

	private static void listarClientes() {
		try (Connection conn = conectar()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM clientes");
			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("id_cliente") + ", Nome: " + rs.getString("nome_cliente"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void adicionarCliente(Scanner scanner) {
		System.out.println("Digite o nome do cliente: ");
		String nome = scanner.nextLine();
		System.out.println("Digite o email do cliente: ");
		String email = scanner.nextLine();
		System.out.println("Digite o telefone do cliente: ");
		String telefone = scanner.nextLine();
		System.out.println("Digite o endereço do cliente: ");
		String endereco = scanner.nextLine();

		try (Connection conn = conectar()) {
			String sql = "INSERT INTO clientes (nome_cliente, email_cliente, tel_cliente, end_cliente) VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, email);
			stmt.setString(3, telefone);
			stmt.setString(4, endereco);
			stmt.executeUpdate();
			System.out.println("Cliente adicionado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void editarCliente(Scanner scanner) {
		System.out.println("Digite o ID do cliente a ser editado: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Digite o novo nome do cliente: ");
		String nome = scanner.nextLine();
		System.out.println("Digite o novo email do cliente: ");
		String email = scanner.nextLine();
		System.out.println("Digite o novo telefone do cliente: ");
		String telefone = scanner.nextLine();
		System.out.println("Digite o novo endereço do cliente: ");
		String endereco = scanner.nextLine();

		try (Connection conn = conectar()) {
			String sql = "UPDATE clientes SET nome_cliente = ?, email_cliente = ?, tel_cliente = ?, end_cliente = ? WHERE id_cliente = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.setString(2, email);
			stmt.setString(3, telefone);
			stmt.setString(4, endereco);
			stmt.setInt(5, id);
			stmt.executeUpdate();
			System.out.println("Cliente editado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void excluirCliente(Scanner scanner) {
		System.out.println("Digite o ID do cliente a ser excluído: ");
		int id = scanner.nextInt();

		try (Connection conn = conectar()) {
			String sql = "DELETE FROM clientes WHERE id_cliente = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			System.out.println("Cliente excluído com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void menuEquipamentos(Scanner scanner) {
		boolean voltar = false;
		while (!voltar) {
			System.out.println("\nMenu Equipamentos:");
			System.out.println("1. Listar Equipamentos");
			System.out.println("2. Voltar");
			System.out.println("Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1:
				listarEquipamentos();
				break;
			case 2:
				voltar = true;
				break;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}

	private static void listarEquipamentos() {
		try (Connection conn = conectar()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM equipamentos");
			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("id_equip") + ", Equipamento: " + rs.getString("nome_equip"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void menuLocacao(Scanner scanner) {
		boolean voltar = false;
		while (!voltar) {
			System.out.println("\nMenu Locação:");
			System.out.println("1. Listar Locações");
			System.out.println("2. Adicionar Locação");
			System.out.println("3. Editar Locação");
			System.out.println("4. Excluir Locação");
			System.out.println("5. Voltar");
			System.out.println("Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine(); 
			switch (opcao) {
			case 1:
				listarLocacoes();
				break;
			case 2:
				adicionarLocacao(scanner);
				break;
			case 3:
				editarLocacao(scanner);
				break;
			case 4:
				excluirLocacao(scanner);
				break;
			case 5:
				voltar = true;
				break;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}

	private static void listarLocacoes() {
		try (Connection conn = conectar()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM locacoes");
			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("id_loc") + ", Data Início: " + rs.getDate("data_inicio") + ", Data Fim: " + rs.getDate("data_fim") + ", Valor Total: " + rs.getDouble("valor_total"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void adicionarLocacao(Scanner scanner) {
	    System.out.println("Digite o ID do Cliente: ");
	    int clienteLoc = scanner.nextInt();
	    scanner.nextLine();
	    
	    System.out.println("Digite o ID de Estudio: ");
	    int estudLoc = scanner.nextInt();
	    scanner.nextLine();

	    System.out.println("Digite a data de início da locação (AAAA-MM-DD): ");
	    String dataInicio = scanner.nextLine();

	    System.out.println("Digite a data de fim da locação (AAAA-MM-DD): ");
	    String dataFim = scanner.nextLine();

	    try (Connection conn = conectar()) {
	        String sql = "INSERT INTO locacoes (cliente_loc, estud_loc, data_inicio, data_fim, valor_total) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        stmt.setInt(1, clienteLoc);
	        stmt.setInt(2, estudLoc);
	        stmt.setDate(3, java.sql.Date.valueOf(dataInicio));
	        stmt.setDate(4, java.sql.Date.valueOf(dataFim));
	        stmt.setDouble(5, 0);
	        stmt.executeUpdate();

	        ResultSet rs = stmt.getGeneratedKeys();
	        int id_loc = 0;
	        if (rs.next()) {
	            id_loc = rs.getInt(1);
	        }

	        double valorTotal = 0;
	        valorTotal = adicionarEquipamentosLocacao(scanner, conn, id_loc, valorTotal);

	        String updateSql = "UPDATE locacoes SET valor_total = ? WHERE id_loc = ?";
	        PreparedStatement updateStmt = conn.prepareStatement(updateSql);
	        updateStmt.setDouble(1, valorTotal);
	        updateStmt.setInt(2, id_loc);
	        updateStmt.executeUpdate();

	        System.out.println("Locação adicionada com sucesso com o valor total de: " + valorTotal);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private static double adicionarEquipamentosLocacao(Scanner scanner, Connection conn, int id_loc, double valorTotal) throws SQLException {
	    System.out.println("Deseja adicionar equipamentos à locação? (s/n): ");
	    String resposta = scanner.nextLine();
	    
	    if (resposta.equalsIgnoreCase("s")) {
	        while (true) {
	            System.out.println("Digite o ID do equipamento: ");
	            int idEquipamento = scanner.nextInt();
	            scanner.nextLine();

	            String sqlEquip = "SELECT valor_equip FROM equipamentos WHERE id_equip = ?";
	            PreparedStatement stmtEquip = conn.prepareStatement(sqlEquip);
	            stmtEquip.setInt(1, idEquipamento);
	            ResultSet rsEquip = stmtEquip.executeQuery();

	            if (rsEquip.next()) {
	                double valorEquip = rsEquip.getDouble("valor_equip");
	                String insertEquipSql = "INSERT INTO locacao_itens (id_loc, id_item, tipo_item) VALUES (?, ?, ?)";
	                PreparedStatement insertEquipStmt = conn.prepareStatement(insertEquipSql);
	                insertEquipStmt.setInt(1, id_loc);
	                insertEquipStmt.setInt(2, idEquipamento);
	                insertEquipStmt.setString(3, "equipamento");
	                insertEquipStmt.executeUpdate();

	                valorTotal += valorEquip;
	                System.out.println("Equipamento adicionado com sucesso! Valor do equipamento: " + valorEquip);
	            } else {
	                System.out.println("Equipamento não encontrado!");
	            }

	            String continuar = "";
	            while (true) {
	                System.out.println("Deseja adicionar mais equipamentos? (s/n): ");
	                continuar = scanner.nextLine();
	                if (continuar.equalsIgnoreCase("s") || continuar.equalsIgnoreCase("n")) {
	                    break;
	                } else {
	                    System.out.println("Resposta inválida! Por favor, digite 's' para sim ou 'n' para não.");
	                }
	            }
	            
	            if (continuar.equalsIgnoreCase("n")) {
	                break;
	            }
	        }
	    }

	    return valorTotal;
	}


	private static void editarLocacao(Scanner scanner) {
	    System.out.println("Digite o ID da locação a ser editada: ");
	    int id = scanner.nextInt();
	    scanner.nextLine();
	    System.out.println("Digite a nova data de início da locação (yyyy-MM-dd): ");
	    String dataInicio = scanner.nextLine();
	    System.out.println("Digite a nova data de fim da locação (yyyy-MM-dd): ");
	    String dataFim = scanner.nextLine();

	    try (Connection conn = conectar()) {
	        String sql = "UPDATE locacoes SET data_inicio = ?, data_fim = ?, WHERE id_loc = ?";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setDate(1, java.sql.Date.valueOf(dataInicio));
	        stmt.setDate(2, java.sql.Date.valueOf(dataFim));
	        stmt.setInt(3, id);
	        stmt.executeUpdate();

	        System.out.println("Locação editada com sucesso!");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	private static void excluirLocacao(Scanner scanner) {
	    System.out.println("Digite o ID da locação a ser excluída: ");
	    int id = scanner.nextInt();
	    scanner.nextLine();
	    try (Connection conn = conectar()) {
	      
	        String sql = "DELETE FROM locacao_itens WHERE id_loc = ?";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, id);
	        stmt.executeUpdate();

	        sql = "DELETE FROM locacao WHERE id_loc = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, id);
	        stmt.executeUpdate();
	        System.out.println("Locação excluída com sucesso!");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	}