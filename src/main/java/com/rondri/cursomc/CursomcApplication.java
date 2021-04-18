package com.rondri.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rondri.cursomc.domain.Categoria;
import com.rondri.cursomc.domain.Cidade;
import com.rondri.cursomc.domain.Cliente;
import com.rondri.cursomc.domain.Endereco;
import com.rondri.cursomc.domain.Estado;
import com.rondri.cursomc.domain.Pagamento;
import com.rondri.cursomc.domain.PagamentoComBoleto;
import com.rondri.cursomc.domain.PagamentoComCartao;
import com.rondri.cursomc.domain.Pedido;
import com.rondri.cursomc.domain.Produto;
import com.rondri.cursomc.domain.enums.EstadoPagamento;
import com.rondri.cursomc.domain.enums.TipoCliente;
import com.rondri.cursomc.repositories.CategoriaRepository;
import com.rondri.cursomc.repositories.CidadeRepository;
import com.rondri.cursomc.repositories.ClienteRepository;
import com.rondri.cursomc.repositories.EnderecoRepository;
import com.rondri.cursomc.repositories.EstadoRepository;
import com.rondri.cursomc.repositories.PagamentoRepository;
import com.rondri.cursomc.repositories.PedidoRepository;
import com.rondri.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository ;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",35.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
			
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlândia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maaria Silva","maria@contoso.com","34576432123",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("32233568","9987447"));
		
		Endereco e1 = new Endereco(null,"rua 1","100","Ap 2","Vila Amato","18055200",cli1,c1);
		Endereco e2 = new Endereco(null,"rua 2","200","Ap 2","Vila Angélica","18055200",cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cli1,e1);
		Pedido ped2 = new Pedido(null,sdf.parse("10/07/2017 19:35"),cli1,e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENDETE,ped2,sdf.parse("20/10/2017 00:00"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
	}

}
