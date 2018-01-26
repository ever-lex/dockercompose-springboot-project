package br.com.ever.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ever.project.service.OrderService;
import br.com.ever.project.service.SaleSnackService;
import br.com.ever.project.vo.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
// @RequestMapping("/fast-food")
public class FastFoodController {

	@Autowired
	SaleSnackService saleSnackService;

	@Autowired
	OrderService orderService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/fast-food/pedidos/salvar", method = RequestMethod.POST)
	public String processForm( @Valid @ModelAttribute(value = "Order") Order order, BindingResult result,
			RedirectAttributes redirectAttributes) {

		log.info("Salvando pedido {}", order);
		
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("message", "SnackEmpty");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			return "redirect:/fast-food/pedidos";
		}

		try {
			
			saleSnackService.saveOrder(order);
			
		} catch (Exception e) {
			log.error("Ocorreu um erro ao salvar o pedido {}", order, e);
			redirectAttributes.addFlashAttribute("message", "Failed");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			if (result.hasErrors()) {
				return "redirect:/fast-food/pedidos";
			}
		}

		redirectAttributes.addFlashAttribute("message", "Success");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/fast-food/pedidos";
	}

	@RequestMapping(value = "/fast-food/pedidos", method = RequestMethod.GET)
	public ModelAndView getOrder() {
		ModelAndView model = null;
		try {
			Order order = orderService.getOrder();
			model = new ModelAndView("pedidos", "order", order);
		} catch (Exception e) {
			log.error("Ocorreu um erro ao carregar itens para venda", e);
		}
		return model;
	}
}
