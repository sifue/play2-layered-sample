package layered.application.controllers

import play.api._
import play.api.mvc._
import layered.domain.Domain
import layered.infrastructure.Infrastructure

object Application extends Controller {
  
  def index = Action {
    Domain.log
    Infrastructure.log
    Ok(layered.ui.views.html.index("Your new application is ready on layered."))
  }
  
}
