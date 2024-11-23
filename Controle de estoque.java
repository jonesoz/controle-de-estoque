import java.util.ArrayList;
import java.util.Scanner;

class Usuario {
    private String nomeUsuario;
    private String senha;

    public Usuario(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }
}

class Produto {
    private String nomeProduto;
    private int quantidade;
    private String validade;

    public Produto(String nomeProduto, int quantidade, String validade) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.validade = validade;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getValidade() {
        return validade;
    }

    public void baixarProduto(int quantidade) {
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
            System.out.println("Baixa realizada com sucesso.");
        } else {
            System.out.println("Quantidade insuficiente no estoque.");
        }
    }

    @Override
    public String toString() {
        return "Produto: " + nomeProduto + ", Quantidade: " + quantidade + ", Validade: " + validade;
    }

public class SistemaLogin {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Produto> estoque = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Login");
            System.out.println("2. Cadastrar");
            System.out.println("0. Sair");
            System.out.print("Digite sua opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    efetuarLogin(scanner);
                    break;
                case 2:
                    cadastrarUsuario(scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarUsuario(Scanner scanner) {
        System.out.print("Digite o nome de usuário: ");
        String nomeUsuario = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.getNomeUsuario().equals(nomeUsuario)) {
                System.out.println("Nome de usuário já existe. Escolha outro.");
                return;
            }
        }

        usuarios.add(new Usuario(nomeUsuario, senha));
        System.out.println("Cadastro realizado com sucesso!");
    }

    private static void efetuarLogin(Scanner scanner) {
        System.out.print("Digite o nome de usuário: ");
        String nomeUsuario = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        if (verificarCredenciais(nomeUsuario, senha)) {
            System.out.println("Login efetuado com sucesso!");
            menuEstoque(scanner);  
        } else {
            System.out.println("Nome de usuário ou senha inválidos.");
        }
    }

    private static boolean verificarCredenciais(String nomeUsuario, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNomeUsuario().equals(nomeUsuario) && usuario.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    private static void menuEstoque(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\nControle de Estoque:");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Consultar Produto");
            System.out.println("3. Baixa de Produto");
            System.out.println("0. Logout");
            System.out.print("Digite sua opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    cadastrarProduto(scanner);
                    break;
                case 2:
                    consultarProduto(scanner);
                    break;
                case 3:
                    baixarProduto(scanner);
                    break;
                case 0:
                    System.out.println("Logout realizado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarProduto(Scanner scanner) {
        System.out.print("Digite o nome do produto: ");
        String nomeProduto = scanner.nextLine();

        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite a validade (dd/mm/yyyy): ");
        String validade = scanner.nextLine();

        estoque.add(new Produto(nomeProduto, quantidade, validade));
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void consultarProduto(Scanner scanner) {
        System.out.print("Digite o nome do produto para consulta: ");
        String nomeProduto = scanner.nextLine();

        for (Produto produto : estoque) {
            if (produto.getNomeProduto().equalsIgnoreCase(nomeProduto)) {
                System.out.println(produto);
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    private static void baixarProduto(Scanner scanner) {
        System.out.print("Digite o nome do produto para dar baixa: ");
        String nomeProduto = scanner.nextLine();

        System.out.print("Digite a quantidade a ser baixada: ");
        int quantidade = scanner.nextInt();

        for (Produto produto : estoque) {
            if (produto.getNomeProduto().equalsIgnoreCase(nomeProduto)) {
                produto.baixarProduto(quantidade);
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }
}
} 