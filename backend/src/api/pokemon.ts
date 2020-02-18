const Pokedex = require('pokedex-promise-v2')

const p = new Pokedex()

export class PokeAPI {

    async getPokemonByName(name)
    {
        const data = await p.getPokemonByName(name).catch(function(error) {
            console.log('Error to find your pokemon :', error)
        })
        return data;
   }
}
