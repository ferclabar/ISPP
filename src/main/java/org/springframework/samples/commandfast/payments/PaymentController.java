package org.springframework.samples.commandfast.payments;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.commandfast.command.Command;
import org.springframework.samples.commandfast.command.CommandService;
import org.springframework.samples.commandfast.mesa.MesaService;
import org.springframework.samples.commandfast.user.AuthoritiesService;
import org.springframework.samples.commandfast.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PaymentController {
	

	private final CommandService commandService;
	private final PaymentService paymentService;
	@Value("${STRIPE_PUBLIC_KEY}")
    private String apiPublicKey;
	
	@Autowired
	public PaymentController(UserService userService, AuthoritiesService authoritiesService, MesaService mesaService, CommandService commandService, PaymentService paymentService) {

		this.commandService = commandService;
		this.paymentService = paymentService;
		
	}
	
	@GetMapping(value = "/payment/{id_comanda}")
	public String payOrder(@PathVariable("id_comanda") int idComanda,  Map<String, Object> model, RedirectAttributes redirectAttrs) {
		Optional<Command> command = commandService.findIdCommands(idComanda);
		if(command.get().getPayment() != null) {
			redirectAttrs.addFlashAttribute("message", "Usted ya ha pagado el pedido.");
			return "redirect:/command/new";
		}
		this.paymentService.makePayment(command.get().getPrice(), command.get().getMesa());
		model.put("stripePublicKey", apiPublicKey);
		model.put("price", command.get().getPrice());
		model.put("id_comanda", idComanda);
		return "payment/charge";
	}
	
	@GetMapping(value = "/payment/subscription")
	public String subscription(Map<String, Object> model) {
		model.put("stripePublicKey", apiPublicKey);
		return "payment/subscription";
	}
    
	@GetMapping(value = "/payment/successPage/{id_comanda}")
	public String paymentSuccessPage(@PathVariable("id_comanda") int idComanda, Map<String, Object> model){
		if(idComanda != 0){	
			Optional<Command> command = commandService.findIdCommands(idComanda);
			Payment payment =this.paymentService.makePayment(command.get().getPrice(), command.get().getMesa());
			payment.setPayHere(false);
			command.get().setPayment(payment);
			this.paymentService.savePayment(payment);
		}
		Optional<Command> command = commandService.findIdCommands(idComanda);
		command.get().setState(false);
		this.commandService.saveCommand(command.get());
		model.put("id_comanda", idComanda);
		return "payment/success";
	}
	
	@GetMapping(value = "/payment/downloadRecipt/{id_comanda}")
	public void downloadRecipt(@PathVariable("id_comanda") int id_comanda, Map<String, Object> model, HttpServletResponse response){
		//generate pdf
		String fileName = this.paymentService.generateRecipt(id_comanda);
		//download pdf
		File file = new File(fileName);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + file.getName()); 
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			
			byte[] buffer = new byte[8192]; //8k buffer
			int bytesRead = -1;
			
			while((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			
			inputStream.close();
			outputStream.flush();
			outputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@GetMapping(value = "/payment/waitPage")
	public String paymentWaitPage(Map<String, Object> model){		
		
		return "payment/wait";
		
	}
	
	@GetMapping(value = "/payment/cash/{id_comanda}")
	public String payHereCash(@PathVariable("id_comanda") int idCommanda,  Map<String, Object> model, RedirectAttributes redirectAttrs){
		Optional<Command> command = commandService.findIdCommands(idCommanda);
		if(command.get().getPayment() != null) {
			redirectAttrs.addFlashAttribute("message", "Usted ya ha pagado el pedido.");
			return "redirect:/command/new";
		}
		Payment payment = this.paymentService.makePayment(command.get().getPrice(), command.get().getMesa());
		payment.setPayHere(true);
		command.get().setPayment(payment);
		command.get().setState(false);
		this.paymentService.savePayment(payment);
		this.commandService.saveCommand(command.get());
		return "redirect:/payment/waitPage";
		
	}
	
	@GetMapping(value = "/payment/creditCard/{id_comanda}")
	public String payHerecCard(@PathVariable("id_comanda") int idCommanda,  Map<String, Object> model, RedirectAttributes redirectAttrs){
		Optional<Command> command = commandService.findIdCommands(idCommanda);
		if(command.get().getPayment() != null) {
			redirectAttrs.addFlashAttribute("message", "Usted ya ha pagado el pedido.");
			return "redirect:/command/new";
		}
		Payment payment =this.paymentService.makePayment(command.get().getPrice(), command.get().getMesa());
		payment.setCreditCard(true);
		payment.setPayHere(true);
		command.get().setPayment(payment);
		command.get().setState(false);
		this.paymentService.savePayment(payment);
		this.commandService.saveCommand(command.get());
		
		return "redirect:/payment/waitPage";
		
	}

}
