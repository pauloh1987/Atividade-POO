import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

class Funcionario {
    private String nome;
    private double salario;
    private Date dataAdmissao;

    public Funcionario(String nome, double salario, String dataAdmissao) throws Exception {
        this.nome = nome;
        this.salario = salario;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.dataAdmissao = dateFormat.parse(dataAdmissao);
    }

    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Nome: " + nome + ", Salário: " + String.format("%.2f", salario) + ", Data de Admissão: " + dateFormat.format(dataAdmissao);
    }
}

class Departamento {
    private String nome;
    private List<Funcionario> funcionarios;

    public Departamento(String nome) {
        this.nome = nome;
        this.funcionarios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        if (funcionarios.size() < 100) {
            funcionarios.add(funcionario);
            System.out.println("Funcionário " + funcionario.getNome() + " adicionado ao departamento " + nome + ".");
        } else {
            System.out.println("Não é possível adicionar mais funcionários ao departamento " + nome + ". Capacidade máxima atingida.");
        }
    }

    public void removerFuncionario(Funcionario funcionario) {
        if (funcionarios.remove(funcionario)) {
            System.out.println("Funcionário " + funcionario.getNome() + " removido do departamento " + nome + ".");
        } else {
            System.out.println("Funcionário " + funcionario.getNome() + " não encontrado no departamento " + nome + ".");
        }
    }

    public void listarFuncionarios() {
        System.out.println("Funcionários no departamento " + nome + ":");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }

    public void darAumento(double percentual) {
        for (Funcionario funcionario : funcionarios) {
            double novoSalario = funcionario.getSalario() * (1 + percentual / 100);
            funcionario.setSalario(novoSalario);
        }
        System.out.println("Aumento de " + percentual + "% concedido aos funcionários do departamento " + nome + ".");
    }
}

class Empresa {
    private String nome;
    private String cnpj;
    private List<Departamento> departamentos;

    public Empresa(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.departamentos = new ArrayList<>();
    }

    public void adicionarDepartamento(Departamento departamento) {
        if (departamentos.size() < 10) {
            departamentos.add(departamento);
            System.out.println("Departamento " + departamento.getNome() + " adicionado à empresa " + nome + ".");
        } else {
            System.out.println("Não é possível adicionar mais departamentos à empresa " + nome + ". Capacidade máxima atingida.");
        }
    }

    public void listarDepartamentos() {
        System.out.println("Departamentos na empresa " + nome + ":");
        for (Departamento departamento : departamentos) {
            System.out.println("- " + departamento.getNome());
        }
    }

    public Departamento buscarDepartamento(String nome) {
        for (Departamento departamento : departamentos) {
            if (departamento.getNome().equalsIgnoreCase(nome)) {
                return departamento;
            }
        }
        return null;
    }

    public void transferirFuncionario(Funcionario funcionario, String nomeDepartamentoOrigem, String nomeDepartamentoDestino) {
        Departamento origem = buscarDepartamento(nomeDepartamentoOrigem);
        Departamento destino = buscarDepartamento(nomeDepartamentoDestino);

        if (origem != null && destino != null) {
            origem.removerFuncionario(funcionario);
            destino.adicionarFuncionario(funcionario);
            System.out.println("Funcionário " + funcionario.getNome() + " transferido de " + nomeDepartamentoOrigem + " para " + nomeDepartamentoDestino + ".");
        } else {
            System.out.println("Transferência não realizada. Verifique os departamentos de origem e destino.");
        }
    }
}

public class GerenciamentoEmpresa {
    public static void main(String[] args) {
        try {
            Funcionario funcionario1 = new Funcionario("João Silva", 3000.00, "10/03/2020");
            Funcionario funcionario2 = new Funcionario("Maria Oliveira", 4500.00, "15/07/2018");
            Funcionario funcionario3 = new Funcionario("Carlos Pereira", 3800.00, "01/09/2021");

            Departamento rh = new Departamento("Recursos Humanos");
            Departamento ti = new Departamento("Tecnologia da Informação");

            Empresa empresa = new Empresa("Tech Solutions", "12.345.678/0001-99");

            empresa.adicionarDepartamento(rh);
            empresa.adicionarDepartamento(ti);

            rh.adicionarFuncionario(funcionario1);
            rh.adicionarFuncionario(funcionario2);
            ti.adicionarFuncionario(funcionario3);

            empresa.listarDepartamentos();
            rh.listarFuncionarios();
            ti.listarFuncionarios();

            rh.darAumento(10);

            empresa.transferirFuncionario(funcionario2, "Recursos Humanos", "Tecnologia da Informação");

            rh.listarFuncionarios();
            ti.listarFuncionarios();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
