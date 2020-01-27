import Pokedex from 'pokedex-promise-v2'

export class PokeAPI {

    constructor() {}

    /**
     * Async function which retrieve pokemon info for the pokemon given
     * @param {string} name : Pokemon name of which we want informations
     */
    async pokemon(name : string) {
        const data = await Pokedex.getPokemonByName(name).catch(function(error) {
            console.log('Error to find your pokemon :', error)
        })
	    return data;
    }

    /**
     * Async function which retrieve ability info for the ability given
     * @param {string} name : Ability name of which we want informations
     */
    async pokemonAbility(name : string) {
        const data = await Pokedex.getAbilityByName(name).catch(function(error) {
            console.log('Error to find your ability :', error)
        })
	    return data;
    }

    /**
     * Async function which retrieve moves info for the ability given
     * @param {string} name : Moves name of which we want informations
     */
    async pokemonMove(name : string) {
        const data = await Pokedex.getMoveByName(name).catch(function(error) {
            console.log('Error to find your move :', error)
        })
	    return data;
    }

    /**
     * Async function which retrieve item info for the ability given
     * @param {string} name : Item name of which we want informations
     */
    async pokemonItem(name : string) {
        const data = await Pokedex.getItemByName(name).catch(function(error) {
            console.log('Error to find your item :', error)
        })
	    return data;
    }
}
